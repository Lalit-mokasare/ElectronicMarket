package com.lcwd.electronics.store.helper;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;





public class Helper {

    private static final Logger log= LoggerFactory.getLogger(Helper.class);


    /**
     * @author Lalit
     * @apiNote this Helper is  used to  get pageble Response
     * @param page
     * @param type
     * @return
     * @param <U>
     * @param <V>
     */

    public static <U, V> PagebleResponse<V> getpagebleResponse(Page<U> page, Class<V> type) {

        log.info("start the Helper class it is provided pageble Response  : {}",page,type);
        List<U> entity = page.getContent();

        List<V> dtoList = entity.stream().map(object -> new ModelMapper().map(object, type)).collect(Collectors.toList());

        PagebleResponse<V> response = new PagebleResponse<>();

        response.setContent(dtoList);
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalElements(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setLastpage(page.isLast());

        log.info("Completed the Helper class it is provided pageble Response  : {}",page,type);
        return response;

    }


}









