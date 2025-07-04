package com.newsaggregator.mapper;

import com.newsaggregator.DTO.NewsTypeResponseDTO;
import com.newsaggregator.Entity.NewsType;

import java.util.ArrayList;
import java.util.List;

public class NewsTypeMapper {

    public static List<NewsTypeResponseDTO> responseDTO(List<NewsType> newsTypeList){
        List<NewsTypeResponseDTO> dtoList=new ArrayList<>();
        for(NewsType newsType: newsTypeList){
           NewsTypeResponseDTO newsTypeResponseDTO= new NewsTypeResponseDTO();
            newsTypeResponseDTO.setNewsType(newsType.getNewsType());
            newsTypeResponseDTO.setId(newsType.getId());
            dtoList.add(newsTypeResponseDTO);
        }

        return dtoList;
    }
}
