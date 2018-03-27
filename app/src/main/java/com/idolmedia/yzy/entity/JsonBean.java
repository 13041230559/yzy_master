package com.idolmedia.yzy.entity;

import com.bigkoo.pickerview.model.IPickerViewData;

import java.util.List;

/**
 * TODO<json数据源>
 *
 * @author: 小嵩
 * @date: 2017/3/16 15:36
 */

public class JsonBean implements IPickerViewData {

    // 实现 IPickerViewData 接口，
    // 这个用来显示在PickerView上面的字符串，
    // PickerView会通过IPickerViewData获取getPickerViewText方法显示出来。
    @Override
    public String getPickerViewText() {
        return this.name;
    }

    /**
     * code : 110000
     * name : 北京市
     * children : [{"code":110100,"name":"北京","children":[{"city_name":"东城区","city_code":110101},{"city_name":"西城区","city_code":110102},{"city_name":"朝阳区","city_code":110105},{"city_name":"丰台区","city_code":110106},{"city_name":"石景山区","city_code":110107},{"city_name":"海淀区","city_code":110108},{"city_name":"门头沟区","city_code":110109},{"city_name":"房山区","city_code":110111},{"city_name":"通州区","city_code":110112},{"city_name":"顺义区","city_code":110113},{"city_name":"昌平区","city_code":110114},{"city_name":"大兴区","city_code":110115},{"city_name":"怀柔区","city_code":110116},{"city_name":"平谷区","city_code":110117},{"city_name":"密云区","city_code":110118},{"city_name":"延庆区","city_code":110119}]}]
     */

    private int code;
    private String name;
    private List<ChildrenBeanX> children;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChildrenBeanX> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBeanX> children) {
        this.children = children;
    }

    public static class ChildrenBeanX {
        /**
         * code : 110100
         * name : 北京
         * children : [{"city_name":"东城区","city_code":110101},{"city_name":"西城区","city_code":110102},{"city_name":"朝阳区","city_code":110105},{"city_name":"丰台区","city_code":110106},{"city_name":"石景山区","city_code":110107},{"city_name":"海淀区","city_code":110108},{"city_name":"门头沟区","city_code":110109},{"city_name":"房山区","city_code":110111},{"city_name":"通州区","city_code":110112},{"city_name":"顺义区","city_code":110113},{"city_name":"昌平区","city_code":110114},{"city_name":"大兴区","city_code":110115},{"city_name":"怀柔区","city_code":110116},{"city_name":"平谷区","city_code":110117},{"city_name":"密云区","city_code":110118},{"city_name":"延庆区","city_code":110119}]
         */

        private int code;
        private String name;
        private List<ChildrenBean> children;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ChildrenBean> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBean> children) {
            this.children = children;
        }

        public static class ChildrenBean {
            /**
             * city_name : 东城区
             * city_code : 110101
             */

            private String city_name;
            private int city_code;

            public String getCity_name() {
                return city_name;
            }

            public void setCity_name(String city_name) {
                this.city_name = city_name;
            }

            public int getCity_code() {
                return city_code;
            }

            public void setCity_code(int city_code) {
                this.city_code = city_code;
            }
        }
    }
}
