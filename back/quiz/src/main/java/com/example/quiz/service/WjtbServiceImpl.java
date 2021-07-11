package com.example.quiz.service;

import com.example.quiz.dto.Encyc;
import com.example.quiz.dto.EncycDetail;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service("wjtbService")
public class WjtbServiceImpl implements WjtbService{

    @Override
    public List<Encyc> search(String keyWord)throws UnsupportedEncodingException {
        keyWord = URLEncoder.encode(keyWord, "UTF-8");

        String myURL = "https://gw-smartall-dev.wjthinkbig.com/search/v1.0/wjPedia/search?SEARCHWORD=" + keyWord;
        StringBuilder sb = new StringBuilder();
        URLConnection urlConn = null;
        InputStreamReader in = null;

        try {
            java.net.URL url = new URL(myURL);
            urlConn = url.openConnection();
            if (urlConn != null)
                urlConn.setReadTimeout(60 * 1000);
            if (urlConn != null && urlConn.getInputStream() != null) {
                in = new InputStreamReader(urlConn.getInputStream(), Charset.defaultCharset());
                //charset 문자 집합의 인코딩을 사용해 urlConn.getInputStream을 문자스트림으로 변환 객체를 생성.
                BufferedReader bufferedReader = new BufferedReader(in);
                //주어진 문자 입력 스트림 inputStream에 대해 기본 크기의 버퍼를 갖는 객체를 생성.
                if (bufferedReader != null) {
                    int cp;
                    while ((cp = bufferedReader.read()) != -1) {
                        sb.append((char) cp);
                    }
                    bufferedReader.close();
                }
            }
            in.close();
        } catch (Exception e) {
            throw new RuntimeException("Exception URL:" + myURL, e);
        }
        String jsonString = sb.toString();

        JSONObject jObject = new JSONObject(jsonString);

        JSONObject obj1 = jObject.getJSONObject("RESP_RESULT");

        JSONArray jsonArray = obj1.getJSONArray("SEARCH_WORD_WJPEDIA_MEAN"); // item들 들어있는 곳
        int length = jsonArray.length();
        List<Encyc> encycArray = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            JSONObject ob = (JSONObject) jsonArray.get(i);
            String dictSeq = (String)ob.get("DICT_SEQ");
            String orgHeadwd=(String)ob.get("ORG_HEADWD");
            String headwd=(String)ob.get("HEADWD");
            JSONArray jsonArray1=ob.getJSONArray("FILE");
            String url="none";
            if(!jsonArray1.isEmpty()){
                JSONObject urlob=(JSONObject) jsonArray1.get(0);
                url=(String)urlob.get("FILE_PATH");
            }
            String des=(String)ob.get("HEAD_WORD_DSCR");
            Encyc encyc=Encyc.builder()
                    .dictSeq(dictSeq)
                    .orgHeadwd(orgHeadwd)
                    .headwd(headwd)
                    .url(url)
                    .des(des).build();
            encycArray.add(encyc);
        }
        //System.out.println("dictSeqArray = " + encycArray);
        return encycArray;
    }

    @Override
    public EncycDetail searchDetail(String dictSeq) {
        String myURL = "https://gw-smartall-dev.wjthinkbig.com/search/v1.0/wjPedia/searchDetail/searchWordNoList/" + dictSeq;
        StringBuilder sb = new StringBuilder();
        URLConnection urlConn = null;
        InputStreamReader in = null;

        try {
            java.net.URL url = new URL(myURL);
            urlConn = url.openConnection();
            if (urlConn != null)
                urlConn.setReadTimeout(60 * 1000);
            if (urlConn != null && urlConn.getInputStream() != null) {
                in = new InputStreamReader(urlConn.getInputStream(), Charset.defaultCharset());
                //charset 문자 집합의 인코딩을 사용해 urlConn.getInputStream을 문자스트림으로 변환 객체를 생성.
                BufferedReader bufferedReader = new BufferedReader(in);
                //주어진 문자 입력 스트림 inputStream에 대해 기본 크기의 버퍼를 갖는 객체를 생성.
                if (bufferedReader != null) {
                    int cp;
                    while ((cp = bufferedReader.read()) != -1) {
                        sb.append((char) cp);
                    }
                    bufferedReader.close();
                }
            }
            in.close();
        } catch (Exception e) {
            throw new RuntimeException("Exception URL:" + myURL, e);
        }
        String jsonString = sb.toString();

        JSONObject jObject = new JSONObject(jsonString);

        JSONArray jsonArray = jObject.getJSONArray("RESP_RESULT");
        JSONObject ob = (JSONObject) jsonArray.get(0);

        String headwdCntt = (String) ob.get("headwd_cntt");
        headwdCntt = headwdCntt.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
        headwdCntt = headwdCntt.replaceAll("<[^>]*>", " ");
        String headWordDscr=(String)ob.get("head_word_dscr");
        String headwd=(String)ob.get("headwd");
        EncycDetail encycDetail=EncycDetail.builder()
                .headwdCntt(headwdCntt)
                .headWordDscr(headWordDscr)
                .headwd(headwd)
                .build();
        //System.out.println("encycDetail = " + encycDetail);
        return encycDetail;
    }

    @Override
    public List<String> searchPhoto(String keyWord) throws UnsupportedEncodingException {
        keyWord = URLEncoder.encode(keyWord, "UTF-8");
        String myURL = "https://gw-smartall-dev.wjthinkbig.com/search/v1.0/photo/search?SEARCHWORD=" + keyWord;
        StringBuilder sb = new StringBuilder();
        URLConnection urlConn = null;
        InputStreamReader in = null;

        try {
            java.net.URL url = new URL(myURL);
            urlConn = url.openConnection();
            if (urlConn != null)
                urlConn.setReadTimeout(60 * 1000);
            if (urlConn != null && urlConn.getInputStream() != null) {
                in = new InputStreamReader(urlConn.getInputStream(), Charset.defaultCharset());
                //charset 문자 집합의 인코딩을 사용해 urlConn.getInputStream을 문자스트림으로 변환 객체를 생성.
                BufferedReader bufferedReader = new BufferedReader(in);
                //주어진 문자 입력 스트림 inputStream에 대해 기본 크기의 버퍼를 갖는 객체를 생성.
                if (bufferedReader != null) {
                    int cp;
                    while ((cp = bufferedReader.read()) != -1) {
                        sb.append((char) cp);
                    }
                    bufferedReader.close();
                }
            }
            in.close();
        } catch (Exception e) {
            throw new RuntimeException("Exception URL:" + myURL, e);
        }
        String jsonString = sb.toString();

        JSONObject jObject = new JSONObject(jsonString);

        JSONObject ob1 = jObject.getJSONObject("RESP_RESULT");
        JSONObject ob2 = ob1.getJSONObject("PHOTOLIB");
        JSONArray jsonArray = ob2.getJSONArray("SEARCH_PHOTOLIB_CONTENTS");

        List<String>result=new ArrayList<>();
        int maxLen=jsonArray.length();
        for(int i=0;i<(maxLen<10?maxLen:10);i++){
            JSONObject ob = (JSONObject) jsonArray.get(i);
            result.add((String) ob.get(("THUMIMG_FILE_PATH")));
        }
        return result;
    }
}
