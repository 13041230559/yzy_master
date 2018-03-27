package com.idolmedia.yzy.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 项目名称：com.idolmedia.yzy.entity
 * 创建人：JOKER.WANG
 * 创建时间：2018/1/17 15:08
 * 描述：账号安全
 */

public class AccountEntity {


    /**
     * msg : 成功
     * datas : {"qq":{"from_type":"phone","nick_name":"13041230559","realuser_id":3651,"account_no":"13041230559"},"wx":{"from_type":"phone","nick_name":"13041230559","realuser_id":3651,"account_no":"13041230559"},"password":"ba954023e9ee590ab082b65b81b6b93d","phone":{"from_type":"phone","nick_name":"13041230559","realuser_id":3651,"account_no":"13041230559"},"main_type":"phone","virtualuser_id":"4275","wb":{"from_type":"phone","nick_name":"13041230559","realuser_id":3651,"account_no":"13041230559"},"email":{"from_type":"phone","nick_name":"13041230559","realuser_id":3651,"account_no":"13041230559"}}
     * resultCode : 01
     */

    private String msg;
    private DatasBean datas;
    private int resultCode;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public static class DatasBean implements Parcelable {
        /**
         * qq : {"from_type":"phone","nick_name":"13041230559","realuser_id":3651,"account_no":"13041230559"}
         * wx : {"from_type":"phone","nick_name":"13041230559","realuser_id":3651,"account_no":"13041230559"}
         * password : ba954023e9ee590ab082b65b81b6b93d
         * phone : {"from_type":"phone","nick_name":"13041230559","realuser_id":3651,"account_no":"13041230559"}
         * main_type : phone
         * virtualuser_id : 4275
         * wb : {"from_type":"phone","nick_name":"13041230559","realuser_id":3651,"account_no":"13041230559"}
         * email : {"from_type":"phone","nick_name":"13041230559","realuser_id":3651,"account_no":"13041230559"}
         */

        private QqBean qq;
        private WxBean wx;
        private String password;
        private PhoneBean phone;
        private String main_type;
        private String virtualuser_id;
        private WbBean wb;
        private EmailBean email;

        public QqBean getQq() {
            return qq;
        }

        public void setQq(QqBean qq) {
            this.qq = qq;
        }

        public WxBean getWx() {
            return wx;
        }

        public void setWx(WxBean wx) {
            this.wx = wx;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public PhoneBean getPhone() {
            return phone;
        }

        public void setPhone(PhoneBean phone) {
            this.phone = phone;
        }

        public String getMain_type() {
            return main_type;
        }

        public void setMain_type(String main_type) {
            this.main_type = main_type;
        }

        public String getVirtualuser_id() {
            return virtualuser_id;
        }

        public void setVirtualuser_id(String virtualuser_id) {
            this.virtualuser_id = virtualuser_id;
        }

        public WbBean getWb() {
            return wb;
        }

        public void setWb(WbBean wb) {
            this.wb = wb;
        }

        public EmailBean getEmail() {
            return email;
        }

        public void setEmail(EmailBean email) {
            this.email = email;
        }

        public static class QqBean implements Parcelable {
            /**
             * from_type : phone
             * nick_name : 13041230559
             * realuser_id : 3651
             * account_no : 13041230559
             */

            private String from_type;
            private String nick_name;
            private int realuser_id;
            private String account_no;

            public String getFrom_type() {
                return from_type;
            }

            public void setFrom_type(String from_type) {
                this.from_type = from_type;
            }

            public String getNick_name() {
                return nick_name;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
            }

            public int getRealuser_id() {
                return realuser_id;
            }

            public void setRealuser_id(int realuser_id) {
                this.realuser_id = realuser_id;
            }

            public String getAccount_no() {
                return account_no;
            }

            public void setAccount_no(String account_no) {
                this.account_no = account_no;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.from_type);
                dest.writeString(this.nick_name);
                dest.writeInt(this.realuser_id);
                dest.writeString(this.account_no);
            }

            public QqBean() {
            }

            protected QqBean(Parcel in) {
                this.from_type = in.readString();
                this.nick_name = in.readString();
                this.realuser_id = in.readInt();
                this.account_no = in.readString();
            }

            public static final Creator<QqBean> CREATOR = new Creator<QqBean>() {
                @Override
                public QqBean createFromParcel(Parcel source) {
                    return new QqBean(source);
                }

                @Override
                public QqBean[] newArray(int size) {
                    return new QqBean[size];
                }
            };
        }

        public static class WxBean implements Parcelable {
            /**
             * from_type : phone
             * nick_name : 13041230559
             * realuser_id : 3651
             * account_no : 13041230559
             */

            private String from_type;
            private String nick_name;
            private int realuser_id;
            private String account_no;

            public String getFrom_type() {
                return from_type;
            }

            public void setFrom_type(String from_type) {
                this.from_type = from_type;
            }

            public String getNick_name() {
                return nick_name;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
            }

            public int getRealuser_id() {
                return realuser_id;
            }

            public void setRealuser_id(int realuser_id) {
                this.realuser_id = realuser_id;
            }

            public String getAccount_no() {
                return account_no;
            }

            public void setAccount_no(String account_no) {
                this.account_no = account_no;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.from_type);
                dest.writeString(this.nick_name);
                dest.writeInt(this.realuser_id);
                dest.writeString(this.account_no);
            }

            public WxBean() {
            }

            protected WxBean(Parcel in) {
                this.from_type = in.readString();
                this.nick_name = in.readString();
                this.realuser_id = in.readInt();
                this.account_no = in.readString();
            }

            public static final Creator<WxBean> CREATOR = new Creator<WxBean>() {
                @Override
                public WxBean createFromParcel(Parcel source) {
                    return new WxBean(source);
                }

                @Override
                public WxBean[] newArray(int size) {
                    return new WxBean[size];
                }
            };
        }

        public static class PhoneBean implements Parcelable {
            /**
             * from_type : phone
             * nick_name : 13041230559
             * realuser_id : 3651
             * account_no : 13041230559
             */

            private String from_type;
            private String nick_name;
            private int realuser_id;
            private String account_no;

            public String getFrom_type() {
                return from_type;
            }

            public void setFrom_type(String from_type) {
                this.from_type = from_type;
            }

            public String getNick_name() {
                return nick_name;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
            }

            public int getRealuser_id() {
                return realuser_id;
            }

            public void setRealuser_id(int realuser_id) {
                this.realuser_id = realuser_id;
            }

            public String getAccount_no() {
                return account_no;
            }

            public void setAccount_no(String account_no) {
                this.account_no = account_no;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.from_type);
                dest.writeString(this.nick_name);
                dest.writeInt(this.realuser_id);
                dest.writeString(this.account_no);
            }

            public PhoneBean() {
            }

            protected PhoneBean(Parcel in) {
                this.from_type = in.readString();
                this.nick_name = in.readString();
                this.realuser_id = in.readInt();
                this.account_no = in.readString();
            }

            public static final Creator<PhoneBean> CREATOR = new Creator<PhoneBean>() {
                @Override
                public PhoneBean createFromParcel(Parcel source) {
                    return new PhoneBean(source);
                }

                @Override
                public PhoneBean[] newArray(int size) {
                    return new PhoneBean[size];
                }
            };
        }

        public static class WbBean implements Parcelable {
            /**
             * from_type : phone
             * nick_name : 13041230559
             * realuser_id : 3651
             * account_no : 13041230559
             */

            private String from_type;
            private String nick_name;
            private int realuser_id;
            private String account_no;

            public String getFrom_type() {
                return from_type;
            }

            public void setFrom_type(String from_type) {
                this.from_type = from_type;
            }

            public String getNick_name() {
                return nick_name;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
            }

            public int getRealuser_id() {
                return realuser_id;
            }

            public void setRealuser_id(int realuser_id) {
                this.realuser_id = realuser_id;
            }

            public String getAccount_no() {
                return account_no;
            }

            public void setAccount_no(String account_no) {
                this.account_no = account_no;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.from_type);
                dest.writeString(this.nick_name);
                dest.writeInt(this.realuser_id);
                dest.writeString(this.account_no);
            }

            public WbBean() {
            }

            protected WbBean(Parcel in) {
                this.from_type = in.readString();
                this.nick_name = in.readString();
                this.realuser_id = in.readInt();
                this.account_no = in.readString();
            }

            public static final Creator<WbBean> CREATOR = new Creator<WbBean>() {
                @Override
                public WbBean createFromParcel(Parcel source) {
                    return new WbBean(source);
                }

                @Override
                public WbBean[] newArray(int size) {
                    return new WbBean[size];
                }
            };
        }

        public static class EmailBean implements Parcelable {
            /**
             * from_type : phone
             * nick_name : 13041230559
             * realuser_id : 3651
             * account_no : 13041230559
             */

            private String from_type;
            private String nick_name;
            private int realuser_id;
            private String account_no;

            public String getFrom_type() {
                return from_type;
            }

            public void setFrom_type(String from_type) {
                this.from_type = from_type;
            }

            public String getNick_name() {
                return nick_name;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
            }

            public int getRealuser_id() {
                return realuser_id;
            }

            public void setRealuser_id(int realuser_id) {
                this.realuser_id = realuser_id;
            }

            public String getAccount_no() {
                return account_no;
            }

            public void setAccount_no(String account_no) {
                this.account_no = account_no;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.from_type);
                dest.writeString(this.nick_name);
                dest.writeInt(this.realuser_id);
                dest.writeString(this.account_no);
            }

            public EmailBean() {
            }

            protected EmailBean(Parcel in) {
                this.from_type = in.readString();
                this.nick_name = in.readString();
                this.realuser_id = in.readInt();
                this.account_no = in.readString();
            }

            public static final Creator<EmailBean> CREATOR = new Creator<EmailBean>() {
                @Override
                public EmailBean createFromParcel(Parcel source) {
                    return new EmailBean(source);
                }

                @Override
                public EmailBean[] newArray(int size) {
                    return new EmailBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable((Parcelable) this.qq, flags);
            dest.writeParcelable((Parcelable) this.wx, flags);
            dest.writeString(this.password);
            dest.writeParcelable((Parcelable) this.phone, flags);
            dest.writeString(this.main_type);
            dest.writeString(this.virtualuser_id);
            dest.writeParcelable((Parcelable) this.wb, flags);
            dest.writeParcelable((Parcelable) this.email, flags);
        }

        public DatasBean() {
        }

        protected DatasBean(Parcel in) {
            this.qq = in.readParcelable(QqBean.class.getClassLoader());
            this.wx = in.readParcelable(WxBean.class.getClassLoader());
            this.password = in.readString();
            this.phone = in.readParcelable(PhoneBean.class.getClassLoader());
            this.main_type = in.readString();
            this.virtualuser_id = in.readString();
            this.wb = in.readParcelable(WbBean.class.getClassLoader());
            this.email = in.readParcelable(EmailBean.class.getClassLoader());
        }

        public static final Parcelable.Creator<DatasBean> CREATOR = new Parcelable.Creator<DatasBean>() {
            @Override
            public DatasBean createFromParcel(Parcel source) {
                return new DatasBean(source);
            }

            @Override
            public DatasBean[] newArray(int size) {
                return new DatasBean[size];
            }
        };
    }
}
