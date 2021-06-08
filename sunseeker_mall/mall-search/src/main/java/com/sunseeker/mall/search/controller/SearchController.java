package com.sunseeker.mall.search.controller;

import com.sunseeker.common.utils.R;
import com.sunseeker.mall.search.service.SearchService;
import com.sunseeker.mall.search.vo.SearchParam;
import com.sunseeker.mall.search.vo.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/search/get")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/result")
    public R getSearchPage(SearchParam searchParam, HttpServletRequest request) {
        searchParam.set_queryString(request.getQueryString());
        System.out.println(searchParam);
        SearchResult result=searchService.getSearchResult(searchParam);
        return R.ok().put("data", result);
    }
}
