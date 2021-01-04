package com.example.shopkuang.bean.bean.login;

public class LoginUserInfoBean {
    /**
     * errno : 0
     * errmsg :
     * data : {"uid":"267a1806-ff7f-4b36-982e-e0020cf08910","username":"shq111","nickname":"孙欢庆","avatar":"","birthday":0,"mark":null}
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
         * uid : 267a1806-ff7f-4b36-982e-e0020cf08910
         * username : shq111
         * nickname : 孙欢庆
         * avatar :
         * birthday : 0
         * mark : null
         */

        private String uid;
        private String username;
        private String nickname;
        private String avatar;
        private int birthday;
        private Object mark;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getBirthday() {
            return birthday;
        }

        public void setBirthday(int birthday) {
            this.birthday = birthday;
        }

        public Object getMark() {
            return mark;
        }

        public void setMark(Object mark) {
            this.mark = mark;
        }
    }
}
