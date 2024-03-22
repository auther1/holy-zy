package org.csu.petstore.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("category") //可以不需要，因为这个类也叫Category，虽然有大写
public class Category {
    @TableId(value = "catid")   //指定主键
    private String categoryId;
    private String name;
    @TableField(value = "descn")    //指定数据域，name因为和表中的数据域名相同所以不用指定
    private String description;
}
