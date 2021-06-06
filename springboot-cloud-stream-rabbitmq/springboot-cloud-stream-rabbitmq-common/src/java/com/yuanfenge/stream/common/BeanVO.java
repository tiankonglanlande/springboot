package com.yuanfenge.stream.common;



import java.io.Serializable;

public class BeanVO  implements Serializable {
    private String sn;
    private String content;


    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "BeanVO{" +
                "sn='" + sn + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static Builder builder(){
        return new Builder();
    }
    public static class Builder{
        private BeanVO beanVO;

        public Builder(){
            this.beanVO=new BeanVO();
        }
        public Builder content(String content){
            beanVO.setContent(content);
            return this;
        }
        public Builder sn(String sn){
            beanVO.setSn(sn);
            return this;
        }

        public BeanVO build() {
            return this.beanVO;
        }
    }
}
