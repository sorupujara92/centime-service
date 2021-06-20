package com.centime.controller;

import com.centime.ValidateRequestBody;
import com.centime.exceptions.NotFoundException;
import com.centime.model.ParentIdColor;
import com.centime.model.ParentIdColorResponse;
import com.centime.model.ServiceRequestBody;
import com.centime.model.Subclasses;
import com.centime.repository.ParentIdColorRepository;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/s4")
public class Service4DB {

    public static final Logger LOGGER = LoggerFactory.getLogger(Service4DB.class);


    @Autowired
    ParentIdColorRepository parentIdColorRepository;

    @ApiOperation(value = "Find elements from db")
    @GetMapping("/final-result")
    public ResponseEntity<List<ParentIdColorResponse>> findAllElements() {
        LOGGER.info("reached s4");
        List<ParentIdColor> parentIdColorList =  parentIdColorRepository.findAll();
        List<ParentIdColorResponse> parentIdColorResponses = new ArrayList<>();
        HashMap<Long,List<ParentIdColor>> resultMap = new HashMap<>();
        ArrayList<ParentIdColor> otherList = new ArrayList<>();
        for(ParentIdColor list : parentIdColorList){
            if(list.getParentId()!=0){
                if(resultMap.get(list.getParentId())==null){
                    ArrayList<ParentIdColor> tempList = new ArrayList<>();
                    tempList.add(list);
                    resultMap.put(list.getParentId(), tempList);
                }else {
                    List<ParentIdColor> result = resultMap.get(list.getParentId());
                    result.add(list);
                    resultMap.put(list.getParentId(), result);
                }
            }else{
                otherList.add(list);
            }
        }
        for(ParentIdColor parentIdColor : otherList){
            ParentIdColorResponse parentIdColorResponse = new ParentIdColorResponse();
            parentIdColorResponse.setName(parentIdColor.getName());
            long id = parentIdColor.getId();
            List<Subclasses> subclasses = new ArrayList<>();
            subclasses = getSubClass(resultMap, id,new Subclasses());
            parentIdColorResponse.setSubclassesList(subclasses);
            parentIdColorResponses.add(parentIdColorResponse);
        }
        return ResponseEntity.ok(parentIdColorResponses);
    }

    private List<Subclasses> getSubClass(HashMap<Long, List<ParentIdColor>> resultMap, long id,Subclasses subclasses) {
        List<ParentIdColor> ParentIdColors = resultMap.get(id);
        ArrayList<Subclasses> subclassesArrayList = new ArrayList<>();
        for (ParentIdColor s : ParentIdColors) {
             Subclasses tempSubclasses = new Subclasses();
             tempSubclasses.setName(s.getName());
             if(resultMap.get(s.getId())!=null){
                 tempSubclasses.setSubclasses(getSubClass(resultMap, s.getId(),tempSubclasses));
             }
             subclassesArrayList.add(tempSubclasses);
        }
        subclasses.setSubclasses(subclassesArrayList);
        return subclassesArrayList;
    }

    @ApiOperation(value = "Find elements from db")
    @GetMapping("/find/{id}")
    public ResponseEntity<ParentIdColor> findById(@PathVariable String id){
        LOGGER.info("reached s4");
        Optional<ParentIdColor> parentIdColor =  parentIdColorRepository.findById(Long.parseLong(id));
        if(parentIdColor.isPresent()){
            return ResponseEntity.ok(parentIdColor.get());
        }else{
            throw new NotFoundException();
        }
    }
}
