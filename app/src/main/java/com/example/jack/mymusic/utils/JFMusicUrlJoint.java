package com.example.jack.mymusic.utils;

import com.google.gson.Gson;

/**
 * 位置：
 * 作用：
 * 时间：2017/6/11
 */

public class JFMusicUrlJoint {
    public static String getNewPlayListUrl(String objectId) {
        if(objectId == null || "".equals(objectId)){
            throw  new  NullPointerException("getNewPlayListUrl(String objectId)  objectId 是空的");
        }
        return basePlayListUrl("NewPlayList", objectId, "songList");
    }

    /**
     * @param className
     * @param objectId
     * @param key
     * @return
     */
    private static String basePlayListUrl(String className, String objectId, String key) {
        QueryRelation queryRelation = new QueryRelation();
        QueryRelation.$relatedToBean $relatedToBean = new QueryRelation.$relatedToBean();
        $relatedToBean.setKey(key);

        QueryRelation.$relatedToBean.ObjectBean objectBean = new QueryRelation.$relatedToBean.ObjectBean();
        objectBean.set__type("Pointer");
        objectBean.setClassName(className);
        objectBean.setObjectId(objectId);

        $relatedToBean.setObject(objectBean);
        queryRelation.set$relatedTo($relatedToBean);

        String json = new Gson().toJson(queryRelation);
        return "?redirectClassNameForKey=songList&where=" + json;
    }


    static class QueryRelation {


        /**
         * $relatedTo : {"object":{"__type":"Pointer","className":"NewPlayList","objectId":"5937ee5d2f301e0058846239"},"key":"songList"}
         */

        private $relatedToBean $relatedTo;

        public $relatedToBean get$relatedTo() {
            return $relatedTo;
        }

        public void set$relatedTo($relatedToBean $relatedTo) {
            this.$relatedTo = $relatedTo;
        }

        public static class $relatedToBean {
            /**
             * object : {"__type":"Pointer","className":"NewPlayList","objectId":"5937ee5d2f301e0058846239"}
             * key : songList
             */

            private ObjectBean object;
            private String key;

            public ObjectBean getObject() {
                return object;
            }

            public void setObject(ObjectBean object) {
                this.object = object;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public static class ObjectBean {
                /**
                 * __type : Pointer
                 * className : NewPlayList
                 * objectId : 5937ee5d2f301e0058846239
                 */

                private String __type;
                private String className;
                private String objectId;

                public String get__type() {
                    return __type;
                }

                public void set__type(String __type) {
                    this.__type = __type;
                }

                public String getClassName() {
                    return className;
                }

                public void setClassName(String className) {
                    this.className = className;
                }

                public String getObjectId() {
                    return objectId;
                }

                public void setObjectId(String objectId) {
                    this.objectId = objectId;
                }
            }
        }
    }
}
