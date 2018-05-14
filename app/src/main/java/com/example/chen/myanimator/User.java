package com.example.chen.myanimator;

/**
 * Created by chen on 2018/4/25.
 */

public class User {

    /**
     * returnCode : 1
     * returnMsg : 操作成功！
     * returnData : {"dwSessionConf":{"dwjbxx_id":"fb24a372c98800","dwxx_id":"fb248c42eb3700000000","yh_id":"fb22139fd8dc00000000","yhzh":"zhyhongyun","yhmm":"l0oP0tzFXB+zqQ08oJkCv/2Ousg=","yhxm":"","sjh":"18610696527","yx":"zhyhongyun_jiayou@163.com","dwmc":"北控三兴信息技术有限公司","zzjgdm":"123213312213-1","gsyyzzh":"808080908090","lxr":"刘帅鹏","lxdz":"联系地址123","szdqq":"520103000000","szdqqmc":"","sessionid":"fb24a4624ce600","czyid":"fb22139fd8dc00000000","czyxm":"","dlsj":"20180420173419","dwlgsc":"","dwlgkhdmc":"","dwlgfwdmc":"","sfcxhq":""},"executeResult":"1"}
     * pageCount : 0
     * rowsCount : 0
     * startNum : 0
     */

    private int returnCode;
    private String returnMsg;
    private ReturnDataBean returnData;
    private int pageCount;
    private int rowsCount;
    private int startNum;

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public ReturnDataBean getReturnData() {
        return returnData;
    }

    public void setReturnData(ReturnDataBean returnData) {
        this.returnData = returnData;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getRowsCount() {
        return rowsCount;
    }

    public void setRowsCount(int rowsCount) {
        this.rowsCount = rowsCount;
    }

    public int getStartNum() {
        return startNum;
    }

    public void setStartNum(int startNum) {
        this.startNum = startNum;
    }

    public static class ReturnDataBean {
        /**
         * dwSessionConf : {"dwjbxx_id":"fb24a372c98800","dwxx_id":"fb248c42eb3700000000","yh_id":"fb22139fd8dc00000000","yhzh":"zhyhongyun","yhmm":"l0oP0tzFXB+zqQ08oJkCv/2Ousg=","yhxm":"","sjh":"18610696527","yx":"zhyhongyun_jiayou@163.com","dwmc":"北控三兴信息技术有限公司","zzjgdm":"123213312213-1","gsyyzzh":"808080908090","lxr":"刘帅鹏","lxdz":"联系地址123","szdqq":"520103000000","szdqqmc":"","sessionid":"fb24a4624ce600","czyid":"fb22139fd8dc00000000","czyxm":"","dlsj":"20180420173419","dwlgsc":"","dwlgkhdmc":"","dwlgfwdmc":"","sfcxhq":""}
         * executeResult : 1
         */

        private DwSessionConfBean dwSessionConf;
        private String executeResult;

        public DwSessionConfBean getDwSessionConf() {
            return dwSessionConf;
        }

        public void setDwSessionConf(DwSessionConfBean dwSessionConf) {
            this.dwSessionConf = dwSessionConf;
        }

        public String getExecuteResult() {
            return executeResult;
        }

        public void setExecuteResult(String executeResult) {
            this.executeResult = executeResult;
        }

        public static class DwSessionConfBean {
            /**
             * dwjbxx_id : fb24a372c98800
             * dwxx_id : fb248c42eb3700000000
             * yh_id : fb22139fd8dc00000000
             * yhzh : zhyhongyun
             * yhmm : l0oP0tzFXB+zqQ08oJkCv/2Ousg=
             * yhxm :
             * sjh : 18610696527
             * yx : zhyhongyun_jiayou@163.com
             * dwmc : 北控三兴信息技术有限公司
             * zzjgdm : 123213312213-1
             * gsyyzzh : 808080908090
             * lxr : 刘帅鹏
             * lxdz : 联系地址123
             * szdqq : 520103000000
             * szdqqmc :
             * sessionid : fb24a4624ce600
             * czyid : fb22139fd8dc00000000
             * czyxm :
             * dlsj : 20180420173419
             * dwlgsc :
             * dwlgkhdmc :
             * dwlgfwdmc :
             * sfcxhq :
             */

            private String dwjbxx_id;
            private String dwxx_id;
            private String yh_id;
            private String yhzh;
            private String yhmm;
            private String yhxm;
            private String sjh;
            private String yx;
            private String dwmc;
            private String zzjgdm;
            private String gsyyzzh;
            private String lxr;
            private String lxdz;
            private String szdqq;
            private String szdqqmc;
            private String sessionid;
            private String czyid;
            private String czyxm;
            private String dlsj;
            private String dwlgsc;
            private String dwlgkhdmc;
            private String dwlgfwdmc;
            private String sfcxhq;

            public String getDwjbxx_id() {
                return dwjbxx_id;
            }

            public void setDwjbxx_id(String dwjbxx_id) {
                this.dwjbxx_id = dwjbxx_id;
            }

            public String getDwxx_id() {
                return dwxx_id;
            }

            public void setDwxx_id(String dwxx_id) {
                this.dwxx_id = dwxx_id;
            }

            public String getYh_id() {
                return yh_id;
            }

            public void setYh_id(String yh_id) {
                this.yh_id = yh_id;
            }

            public String getYhzh() {
                return yhzh;
            }

            public void setYhzh(String yhzh) {
                this.yhzh = yhzh;
            }

            public String getYhmm() {
                return yhmm;
            }

            public void setYhmm(String yhmm) {
                this.yhmm = yhmm;
            }

            public String getYhxm() {
                return yhxm;
            }

            public void setYhxm(String yhxm) {
                this.yhxm = yhxm;
            }

            public String getSjh() {
                return sjh;
            }

            public void setSjh(String sjh) {
                this.sjh = sjh;
            }

            public String getYx() {
                return yx;
            }

            public void setYx(String yx) {
                this.yx = yx;
            }

            public String getDwmc() {
                return dwmc;
            }

            public void setDwmc(String dwmc) {
                this.dwmc = dwmc;
            }

            public String getZzjgdm() {
                return zzjgdm;
            }

            public void setZzjgdm(String zzjgdm) {
                this.zzjgdm = zzjgdm;
            }

            public String getGsyyzzh() {
                return gsyyzzh;
            }

            public void setGsyyzzh(String gsyyzzh) {
                this.gsyyzzh = gsyyzzh;
            }

            public String getLxr() {
                return lxr;
            }

            public void setLxr(String lxr) {
                this.lxr = lxr;
            }

            public String getLxdz() {
                return lxdz;
            }

            public void setLxdz(String lxdz) {
                this.lxdz = lxdz;
            }

            public String getSzdqq() {
                return szdqq;
            }

            public void setSzdqq(String szdqq) {
                this.szdqq = szdqq;
            }

            public String getSzdqqmc() {
                return szdqqmc;
            }

            public void setSzdqqmc(String szdqqmc) {
                this.szdqqmc = szdqqmc;
            }

            public String getSessionid() {
                return sessionid;
            }

            public void setSessionid(String sessionid) {
                this.sessionid = sessionid;
            }

            public String getCzyid() {
                return czyid;
            }

            public void setCzyid(String czyid) {
                this.czyid = czyid;
            }

            public String getCzyxm() {
                return czyxm;
            }

            public void setCzyxm(String czyxm) {
                this.czyxm = czyxm;
            }

            public String getDlsj() {
                return dlsj;
            }

            public void setDlsj(String dlsj) {
                this.dlsj = dlsj;
            }

            public String getDwlgsc() {
                return dwlgsc;
            }

            public void setDwlgsc(String dwlgsc) {
                this.dwlgsc = dwlgsc;
            }

            public String getDwlgkhdmc() {
                return dwlgkhdmc;
            }

            public void setDwlgkhdmc(String dwlgkhdmc) {
                this.dwlgkhdmc = dwlgkhdmc;
            }

            public String getDwlgfwdmc() {
                return dwlgfwdmc;
            }

            public void setDwlgfwdmc(String dwlgfwdmc) {
                this.dwlgfwdmc = dwlgfwdmc;
            }

            public String getSfcxhq() {
                return sfcxhq;
            }

            public void setSfcxhq(String sfcxhq) {
                this.sfcxhq = sfcxhq;
            }
        }
    }
}
