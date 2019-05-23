package com.rinkaze.wanandroid.bean;

import java.util.List;

/**
 * Created by 灵风 on 2019/5/23.
 */

public class MyCollectBean {

    /**
     * data : {"over":true,"pageCount":1,"total":4,"curPage":1,"offset":0,"size":20,"datas":[{"publishTime":1558446768000,"visible":0,"niceDate":"1天前","author":"hylexus","zan":0,"origin":"","chapterName":"多线程","link":"https://www.jianshu.com/p/43e8e3a8b688","title":"java多线程-07-Lock和Condition","userId":24185,"originId":8475,"envelopePic":"","chapterId":308,"id":62520,"courseId":13,"desc":""},{"publishTime":1558426113000,"visible":0,"niceDate":"2天前","author":"Ruheng","zan":0,"origin":"","chapterName":"基础UI控件","link":"http://www.jianshu.com/p/6843f332c8df","title":"Android图文混排实现方式详解","userId":24185,"originId":1165,"envelopePic":"","chapterId":26,"id":62464,"courseId":13,"desc":"详解Android图文混排实现。"},{"publishTime":1558410304000,"visible":0,"niceDate":"2天前","author":"鸿洋","zan":0,"origin":"","chapterName":"技术周刊","link":"https://www.wanandroid.com/blog/show/2561","title":"各大互联网公司对外分享链接汇总 | 不可抗力，MockAPI永久下线","userId":24185,"originId":8389,"envelopePic":"","chapterId":449,"id":62392,"courseId":13,"desc":""},{"publishTime":1558410302000,"visible":0,"niceDate":"2天前","author":"鸿洋","zan":0,"origin":"","chapterName":"鸿洋","link":"https://mp.weixin.qq.com/s/t2uDAOTiLamKxAd1CmUbtQ","title":"做相机？首学Google 的开源相机方案！","userId":24185,"originId":8455,"envelopePic":"","chapterId":408,"id":62391,"courseId":13,"desc":""}]}
     * errorCode : 0
     * errorMsg :
     */
    private DataEntity data;
    private int errorCode;
    private String errorMsg;

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public DataEntity getData() {
        return data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public class DataEntity {
        /**
         * over : true
         * pageCount : 1
         * total : 4
         * curPage : 1
         * offset : 0
         * size : 20
         * datas : [{"publishTime":1558446768000,"visible":0,"niceDate":"1天前","author":"hylexus","zan":0,"origin":"","chapterName":"多线程","link":"https://www.jianshu.com/p/43e8e3a8b688","title":"java多线程-07-Lock和Condition","userId":24185,"originId":8475,"envelopePic":"","chapterId":308,"id":62520,"courseId":13,"desc":""},{"publishTime":1558426113000,"visible":0,"niceDate":"2天前","author":"Ruheng","zan":0,"origin":"","chapterName":"基础UI控件","link":"http://www.jianshu.com/p/6843f332c8df","title":"Android图文混排实现方式详解","userId":24185,"originId":1165,"envelopePic":"","chapterId":26,"id":62464,"courseId":13,"desc":"详解Android图文混排实现。"},{"publishTime":1558410304000,"visible":0,"niceDate":"2天前","author":"鸿洋","zan":0,"origin":"","chapterName":"技术周刊","link":"https://www.wanandroid.com/blog/show/2561","title":"各大互联网公司对外分享链接汇总 | 不可抗力，MockAPI永久下线","userId":24185,"originId":8389,"envelopePic":"","chapterId":449,"id":62392,"courseId":13,"desc":""},{"publishTime":1558410302000,"visible":0,"niceDate":"2天前","author":"鸿洋","zan":0,"origin":"","chapterName":"鸿洋","link":"https://mp.weixin.qq.com/s/t2uDAOTiLamKxAd1CmUbtQ","title":"做相机？首学Google 的开源相机方案！","userId":24185,"originId":8455,"envelopePic":"","chapterId":408,"id":62391,"courseId":13,"desc":""}]
         */
        private boolean over;
        private int pageCount;
        private int total;
        private int curPage;
        private int offset;
        private int size;
        private List<DatasEntity> datas;

        public void setOver(boolean over) {
            this.over = over;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public void setDatas(List<DatasEntity> datas) {
            this.datas = datas;
        }

        public boolean isOver() {
            return over;
        }

        public int getPageCount() {
            return pageCount;
        }

        public int getTotal() {
            return total;
        }

        public int getCurPage() {
            return curPage;
        }

        public int getOffset() {
            return offset;
        }

        public int getSize() {
            return size;
        }

        public List<DatasEntity> getDatas() {
            return datas;
        }

        public class DatasEntity {
            /**
             * publishTime : 1558446768000
             * visible : 0
             * niceDate : 1天前
             * author : hylexus
             * zan : 0
             * origin :
             * chapterName : 多线程
             * link : https://www.jianshu.com/p/43e8e3a8b688
             * title : java多线程-07-Lock和Condition
             * userId : 24185
             * originId : 8475
             * envelopePic :
             * chapterId : 308
             * id : 62520
             * courseId : 13
             * desc :
             */
            private long publishTime;
            private int visible;
            private String niceDate;
            private String author;
            private int zan;
            private String origin;
            private String chapterName;
            private String link;
            private String title;
            private int userId;
            private int originId;
            private String envelopePic;
            private int chapterId;
            private int id;
            private int courseId;
            private String desc;

            public void setPublishTime(long publishTime) {
                this.publishTime = publishTime;
            }

            public void setVisible(int visible) {
                this.visible = visible;
            }

            public void setNiceDate(String niceDate) {
                this.niceDate = niceDate;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public void setZan(int zan) {
                this.zan = zan;
            }

            public void setOrigin(String origin) {
                this.origin = origin;
            }

            public void setChapterName(String chapterName) {
                this.chapterName = chapterName;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public void setOriginId(int originId) {
                this.originId = originId;
            }

            public void setEnvelopePic(String envelopePic) {
                this.envelopePic = envelopePic;
            }

            public void setChapterId(int chapterId) {
                this.chapterId = chapterId;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setCourseId(int courseId) {
                this.courseId = courseId;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public long getPublishTime() {
                return publishTime;
            }

            public int getVisible() {
                return visible;
            }

            public String getNiceDate() {
                return niceDate;
            }

            public String getAuthor() {
                return author;
            }

            public int getZan() {
                return zan;
            }

            public String getOrigin() {
                return origin;
            }

            public String getChapterName() {
                return chapterName;
            }

            public String getLink() {
                return link;
            }

            public String getTitle() {
                return title;
            }

            public int getUserId() {
                return userId;
            }

            public int getOriginId() {
                return originId;
            }

            public String getEnvelopePic() {
                return envelopePic;
            }

            public int getChapterId() {
                return chapterId;
            }

            public int getId() {
                return id;
            }

            public int getCourseId() {
                return courseId;
            }

            public String getDesc() {
                return desc;
            }
        }
    }
}
