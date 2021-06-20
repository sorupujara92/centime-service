package com.centime;

import com.centime.exceptions.BadRequestException;
import com.centime.model.ServiceRequestBody;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateRequestBody {

    static Pattern p = Pattern.compile("[^A-Za-z0-9]");

    public static void validateServiceRequest(ServiceRequestBody serviceRequestBody){
        String fname= serviceRequestBody.getName();
        if (fname==null){
            throw new BadRequestException();
        }
        Matcher m = p.matcher(fname);
        if (m.find()){
            throw new BadRequestException();
        }

        String sname= serviceRequestBody.getSurName();
        if (sname==null){
            throw new BadRequestException();
        }
        m = p.matcher(sname);
        if (m.find()){
            throw new BadRequestException();
        }
    }
}
