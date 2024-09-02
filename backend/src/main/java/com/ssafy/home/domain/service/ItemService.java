package com.ssafy.home.domain.service;

import com.ssafy.home.domain.dto.ItemCoordDto;
import com.ssafy.home.domain.dto.ItemDto;
import com.ssafy.home.domain.mapper.ItemMapper;
import com.ssafy.home.domain.mapper.ZzimMapper;
import com.ssafy.home.domain.request.CoordinateRangeRequest;
import org.json.simple.JSONArray;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;

import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Service
public class ItemService {

    @Value("${kakao.api.key}")
    String apiKey;
    @Value("${kakao.url}")
    String apiUrl;
    @Value("${img.path}")
    String path;

    private final ItemMapper itemMapper;
    private final ZzimMapper zzimMapper;

    public ItemService(ItemMapper itemMapper, ZzimMapper zzimMapper) {
        this.itemMapper = itemMapper;
        this.zzimMapper = zzimMapper;
    }

    @Transactional
    public void insertItem(ItemDto itemDto, List<MultipartFile> img, int memberId) throws ParseException {
        itemDto.setMemberId(memberId);
        String json = getAddress(itemDto.getAddress());

        ArrayList<Float> xy = changeToJSON(json);

        if(xy != null){
            itemDto.setLongitude(xy.get(0));
            itemDto.setLatitude(xy.get(1));
        }

        itemMapper.insertItem(itemDto);

        for (MultipartFile photo : img) {
            try {
                String url = savePhoto(photo);
                itemMapper.insertImg(itemMapper.id(), url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String savePhoto(MultipartFile photo) throws IOException {
        // 파일을 저장할 디렉토리 경로를 지정합니다.
        String uploadDir = path;

        // 디렉토리가 존재하지 않으면 생성합니다.
        Path uploadPath = Paths.get(uploadDir);

        // 저장할 파일의 이름을 설정합니다.
        String originalFileName = photo.getOriginalFilename();
        String safeFileName = originalFileName.replaceAll(" ", "_");
        String fileName = System.currentTimeMillis() + "_" + safeFileName;

        // 파일을 저장할 전체 경로를 생성합니다.
        Path filePath = uploadPath.resolve(fileName);

        // 파일을 로컬 디스크에 저장합니다.
        Files.copy(photo.getInputStream(), filePath);

        // db 저장은 파일 이름만
        return fileName;
    }

    private String getAddress(String address) {
        String jsonString = null;

        try {
            address = URLEncoder.encode(address, "UTF-8");
            String addr = apiUrl + "?query=" + address;
            URL url = new URL(addr);
            URLConnection conn = url.openConnection();
            conn.setRequestProperty("Authorization", "KakaoAK " + apiKey);
            BufferedReader json = null;
            json = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuffer docJson = new StringBuffer();
            String line;
            while ((line = json.readLine()) != null) {
                docJson.append(line);
            }
            jsonString = docJson.toString();
            json.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return jsonString;
    }

    public ArrayList<Float> changeToJSON(String jsonString) throws ParseException {
        ArrayList<Float> array = new ArrayList<>();
        JSONParser parser = new JSONParser();
        JSONObject document = (JSONObject)parser.parse(jsonString);
        JSONArray jsonArray = (JSONArray) document.get("documents");

        if(jsonArray.size() == 0){
            return null;
        }

        JSONObject position = (JSONObject)jsonArray.get(0);
        float lon = Float.parseFloat((String) position.get("x"));
        float lat = Float.parseFloat((String) position.get("y"));
        array.add(lon);
        array.add(lat);
        return array;
    }

    @Transactional
    public int deleteItem(int itemId, int memberId) {
        System.out.println(itemId + " " + memberId);
        return itemMapper.deleteItem(itemId, memberId);
    }

    @Transactional
    public void sold(int itemId, int memberId) {
        ItemDto item = itemMapper.soldSearch(itemId, memberId);

        if(item != null){
            itemMapper.sold(item.getItemId(), item.getMemberId(), !item.isSold());
        }
    }

    public List<ItemCoordDto> search(CoordinateRangeRequest request, Object memberId) {
        List<ItemCoordDto> list = itemMapper.search(request);
        List<ItemCoordDto> list2 = new ArrayList<>();

        if(memberId != null) {
            for(ItemCoordDto l : list) {
                boolean zzim = false;
                int cnt = zzimMapper.checkItem((int) memberId, l.getItemId());

                if (cnt == 1) {
                    zzim = true;
                }
                l.setZzim(zzim);
                list2.add(l);
            }
            return list2;
        }
        return list;
    }

    public List<ItemCoordDto> myItem(int memberId) {
        return itemMapper.myItem(memberId);
    }

    public ItemDto searchDetail(int itemId, Object memberId) {
        ItemDto item = itemMapper.searchDetail(itemId);
        boolean zzim = false;

        List<String> img = itemMapper.getImg(itemId);
        item.setImg(img);

        if(memberId != null) {
            int cnt = zzimMapper.checkItem((int) memberId, item.getItemId());

            if (cnt == 1) {
                zzim = true;
            }
            item.setZzim(zzim);
        }
        return item;
    }

    @Transactional
    public int update(ItemDto itemDto) {
        return itemMapper.update(itemDto);
    }
}
