package io.github.firstblood1985.c2visualizer.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * usage of this class: BaseModel
 * created by limin @ 2022/4/18
 */
public class BaseModel implements Serializable {

    private static final long serialVersionUID = 5896369625279555056L;

    @Override
    public String toString(){
        try {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
        }catch (Exception e)
        {
            return this.getClass().getName();
        }
    }

}
