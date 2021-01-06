package com.live.model.bean;

public class RoomLiveBean {

    /**
     * errno : 0
     * errmsg :
     * data : {"id":2,"name":"会玩的房间","icon":"https://shop-app1.oss-cn-beijing.aliyuncs.com/live/1/room.jpg","owner":"7b965656-70eb-4726-b4a7-c52366861485","push_url":"rtmp://livepush.cdwan.cn/shop/7b965656-70eb-4726-b4a7-c52366861485?txSecret=9e978206aa8581284182c53b1325be7a&txTime=5ff5c8a0","play_url":"rtmp://live.cdwan.cn/shop/7b965656-70eb-4726-b4a7-c52366861485?txSecret=9e978206aa8581284182c53b1325be7a&txTime=5ff5c8a0","isopen":1,"status":1}
     */

    private int errno;
    private String errmsg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 2
         * name : 会玩的房间
         * icon : https://shop-app1.oss-cn-beijing.aliyuncs.com/live/1/room.jpg
         * owner : 7b965656-70eb-4726-b4a7-c52366861485
         * push_url : rtmp://livepush.cdwan.cn/shop/7b965656-70eb-4726-b4a7-c52366861485?txSecret=9e978206aa8581284182c53b1325be7a&txTime=5ff5c8a0
         * play_url : rtmp://live.cdwan.cn/shop/7b965656-70eb-4726-b4a7-c52366861485?txSecret=9e978206aa8581284182c53b1325be7a&txTime=5ff5c8a0
         * isopen : 1
         * status : 1
         */

        private int id;
        private String name;
        private String icon;
        private String owner;
        private String push_url;
        private String play_url;
        private int isopen;
        private int status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public String getPush_url() {
            return push_url;
        }

        public void setPush_url(String push_url) {
            this.push_url = push_url;
        }

        public String getPlay_url() {
            return play_url;
        }

        public void setPlay_url(String play_url) {
            this.play_url = play_url;
        }

        public int getIsopen() {
            return isopen;
        }

        public void setIsopen(int isopen) {
            this.isopen = isopen;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
