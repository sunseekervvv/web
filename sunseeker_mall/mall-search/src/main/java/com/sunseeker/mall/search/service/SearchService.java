package com.sunseeker.mall.search.service;


import com.sunseeker.mall.search.vo.SearchParam;
import com.sunseeker.mall.search.vo.SearchResult;

public interface SearchService {
    SearchResult getSearchResult(SearchParam searchParam);
}
