package com.example.shopkuang.bean.bean.live;

public class SetRoomBean {
    /**
     * errno : 0
     * errmsg :
     * data : {"msg":"创建房间成功","id":2,"name":"小孙的直播间","icon":"https://2002aa.oss-cn-beijing.aliyuncs.com/1609243608806.jpg"}
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
         * msg : 创建房间成功
         * id : 2
         * name : 小孙的直播间
         * icon : https://2002aa.oss-cn-beijing.aliyuncs.com/1609243608806.jpg
         */

        private String msg;
        private int id;
        private String name;
        private String icon;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

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
    }
}
