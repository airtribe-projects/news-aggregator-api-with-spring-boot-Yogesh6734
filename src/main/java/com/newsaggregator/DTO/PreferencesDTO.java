package com.newsaggregator.DTO;

import com.newsaggregator.Entity.NewsType;
import lombok.Data;
import java.util.List;

@Data
public class PreferencesDTO {
    private List<Integer> preferences;
}
