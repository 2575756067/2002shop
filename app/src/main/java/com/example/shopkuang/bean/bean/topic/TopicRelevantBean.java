package com.example.shopkuang.bean.bean.topic;


import java.util.List;

//todo 专题详情相关推荐数据
public class TopicRelevantBean {

    /**
     * errno : 0
     * errmsg :
     * data : [{"id":314,"title":"关爱他成长的每一个足迹","price_info":0,"scene_pic_url":"https://yanxuan.nosdn.127.net/14943267735961674.jpg","subtitle":"专业运动品牌同厂，毛毛虫鞋买二送一"},{"id":313,"title":"一次解决5个节日送礼难题","price_info":59.9,"scene_pic_url":"https://yanxuan.nosdn.127.net/14942996754171334.jpg","subtitle":"这些就是他们想要的礼物清单"},{"id":300,"title":"秒杀化学洗涤剂的纯天然皂","price_info":0,"scene_pic_url":"https://yanxuan.nosdn.127.net/14939843143621089.jpg","subtitle":"前段时间有朋友跟我抱怨，和婆婆住到一起才发现生活理念有太多不和。别的不提，光是洗..."},{"id":299,"title":"买过的人都说它是差旅神器","price_info":0,"scene_pic_url":"https://yanxuan.nosdn.127.net/14938873919030679.jpg","subtitle":"许多人经历过旅途中内裤洗晾不便的烦恼，尤其与旅伴同居一室时，晾在卫生间里的内裤更..."}]
     */

    private int errno;
    private String errmsg;
    private List<DataBean> data;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 314
         * title : 关爱他成长的每一个足迹
         * price_info : 0
         * scene_pic_url : https://yanxuan.nosdn.127.net/14943267735961674.jpg
         * subtitle : 专业运动品牌同厂，毛毛虫鞋买二送一
         */

        private int id;
        private String title;
        private int price_info;
        private String scene_pic_url;
        private String subtitle;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getPrice_info() {
            return price_info;
        }

        public void setPrice_info(int price_info) {
            this.price_info = price_info;
        }

        public String getScene_pic_url() {
            return scene_pic_url;
        }

        public void setScene_pic_url(String scene_pic_url) {
            this.scene_pic_url = scene_pic_url;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }
    }
}

