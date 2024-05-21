package com.ssafy.home.domain.service;

import com.ssafy.home.domain.dto.ItemDto;
import com.ssafy.home.domain.mapper.ItemMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Service
public class ItemService {

    private final ItemMapper itemMapper;

    public ItemService(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    @Transactional
    public void inserItem(ItemDto itemDto, int memberId) {
        itemDto.setMemberId(memberId);

        double[] xy = geo(itemDto.getAddress());

        if(xy != null){
            System.out.println("A");
            itemDto.setLongitude(xy[0]);
            itemDto.setLatitude(xy[1]);
        }
        itemMapper.insertItem(itemDto);

        for (MultipartFile photo : itemDto.getImg()) {
            try {
                String url = savePhoto(photo);
                itemMapper.insertImg(itemDto.getItemId(), url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String savePhoto(MultipartFile photo) throws IOException {
        // 파일을 저장할 디렉토리 경로를 지정합니다.
        String uploadDir = "/path/to/upload/directory"; // 실제 파일을 저장할 디렉토리 경로로 변경하세요.

        // 디렉토리가 존재하지 않으면 생성합니다.
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // 저장할 파일의 이름을 설정합니다.
        String fileName = System.currentTimeMillis() + "_" + photo.getOriginalFilename();

        // 파일을 저장할 전체 경로를 생성합니다.
        Path filePath = uploadPath.resolve(fileName);

        // 파일을 로컬 디스크에 저장합니다.
        Files.copy(photo.getInputStream(), filePath);

        // 저장된 파일의 경로를 반환합니다.
        return filePath.toString();
    }

    private double[] geo(String address) {
        String apikey = "D324ED1E-B082-31EE-AF7E-355A0DC1DE8E";
        String searchType = "parcel";
        String searchAddr = address;
        String epsg = "epsg:4326";

        StringBuilder sb = new StringBuilder("https://api.vworld.kr/req/address");
        sb.append("?service=address");
        sb.append("&request=getCoord");
        sb.append("&format=json");
        sb.append("&crs=" + epsg);
        sb.append("&key=" + apikey);
        sb.append("&type=" + searchType);
        sb.append("&address=" + URLEncoder.encode(searchAddr, StandardCharsets.UTF_8));

        try{
            URL url = new URL(sb.toString());
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));

            JSONParser jspa = new JSONParser();
            JSONObject jsob = (JSONObject) jspa.parse(reader);
            JSONObject jsrs = (JSONObject) jsob.get("response");
            JSONObject jsResult = (JSONObject) jsrs.get("result");

            if(jsResult == null){
                return null;
            }
            JSONObject jspoitn = (JSONObject) jsResult.get("point");

            String xStr = (String) jspoitn.get("x");
            String yStr = (String) jspoitn.get("y");

            double x = Double.parseDouble(xStr);
            double y = Double.parseDouble(yStr);

            System.out.println(x + " " + y);

            double[] value = {x, y};
            return value;
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
