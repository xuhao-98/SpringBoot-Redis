package com.xuhao.springcloudredis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 描述：
 *
 * @author XuHao
 * @date 2020/9/3  13:57
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class User implements Serializable{
    private String name;
    private Integer age;
}
