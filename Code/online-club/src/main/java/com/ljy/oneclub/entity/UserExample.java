package com.ljy.oneclub.entity;

import java.util.ArrayList;
import java.util.List;

public class UserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andUIdIsNull() {
            addCriterion("u_id is null");
            return (Criteria) this;
        }

        public Criteria andUIdIsNotNull() {
            addCriterion("u_id is not null");
            return (Criteria) this;
        }

        public Criteria andUIdEqualTo(Integer value) {
            addCriterion("u_id =", value, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdNotEqualTo(Integer value) {
            addCriterion("u_id <>", value, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdGreaterThan(Integer value) {
            addCriterion("u_id >", value, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("u_id >=", value, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdLessThan(Integer value) {
            addCriterion("u_id <", value, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdLessThanOrEqualTo(Integer value) {
            addCriterion("u_id <=", value, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdIn(List<Integer> values) {
            addCriterion("u_id in", values, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdNotIn(List<Integer> values) {
            addCriterion("u_id not in", values, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdBetween(Integer value1, Integer value2) {
            addCriterion("u_id between", value1, value2, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdNotBetween(Integer value1, Integer value2) {
            addCriterion("u_id not between", value1, value2, "uId");
            return (Criteria) this;
        }

        public Criteria andUNameIsNull() {
            addCriterion("u_name is null");
            return (Criteria) this;
        }

        public Criteria andUNameIsNotNull() {
            addCriterion("u_name is not null");
            return (Criteria) this;
        }

        public Criteria andUNameEqualTo(String value) {
            addCriterion("u_name =", value, "uName");
            return (Criteria) this;
        }

        public Criteria andUNameNotEqualTo(String value) {
            addCriterion("u_name <>", value, "uName");
            return (Criteria) this;
        }

        public Criteria andUNameGreaterThan(String value) {
            addCriterion("u_name >", value, "uName");
            return (Criteria) this;
        }

        public Criteria andUNameGreaterThanOrEqualTo(String value) {
            addCriterion("u_name >=", value, "uName");
            return (Criteria) this;
        }

        public Criteria andUNameLessThan(String value) {
            addCriterion("u_name <", value, "uName");
            return (Criteria) this;
        }

        public Criteria andUNameLessThanOrEqualTo(String value) {
            addCriterion("u_name <=", value, "uName");
            return (Criteria) this;
        }

        public Criteria andUNameLike(String value) {
            addCriterion("u_name like", value, "uName");
            return (Criteria) this;
        }

        public Criteria andUNameNotLike(String value) {
            addCriterion("u_name not like", value, "uName");
            return (Criteria) this;
        }

        public Criteria andUNameIn(List<String> values) {
            addCriterion("u_name in", values, "uName");
            return (Criteria) this;
        }

        public Criteria andUNameNotIn(List<String> values) {
            addCriterion("u_name not in", values, "uName");
            return (Criteria) this;
        }

        public Criteria andUNameBetween(String value1, String value2) {
            addCriterion("u_name between", value1, value2, "uName");
            return (Criteria) this;
        }

        public Criteria andUNameNotBetween(String value1, String value2) {
            addCriterion("u_name not between", value1, value2, "uName");
            return (Criteria) this;
        }

        public Criteria andUPasswordIsNull() {
            addCriterion("u_password is null");
            return (Criteria) this;
        }

        public Criteria andUPasswordIsNotNull() {
            addCriterion("u_password is not null");
            return (Criteria) this;
        }

        public Criteria andUPasswordEqualTo(String value) {
            addCriterion("u_password =", value, "uPassword");
            return (Criteria) this;
        }

        public Criteria andUPasswordNotEqualTo(String value) {
            addCriterion("u_password <>", value, "uPassword");
            return (Criteria) this;
        }

        public Criteria andUPasswordGreaterThan(String value) {
            addCriterion("u_password >", value, "uPassword");
            return (Criteria) this;
        }

        public Criteria andUPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("u_password >=", value, "uPassword");
            return (Criteria) this;
        }

        public Criteria andUPasswordLessThan(String value) {
            addCriterion("u_password <", value, "uPassword");
            return (Criteria) this;
        }

        public Criteria andUPasswordLessThanOrEqualTo(String value) {
            addCriterion("u_password <=", value, "uPassword");
            return (Criteria) this;
        }

        public Criteria andUPasswordLike(String value) {
            addCriterion("u_password like", value, "uPassword");
            return (Criteria) this;
        }

        public Criteria andUPasswordNotLike(String value) {
            addCriterion("u_password not like", value, "uPassword");
            return (Criteria) this;
        }

        public Criteria andUPasswordIn(List<String> values) {
            addCriterion("u_password in", values, "uPassword");
            return (Criteria) this;
        }

        public Criteria andUPasswordNotIn(List<String> values) {
            addCriterion("u_password not in", values, "uPassword");
            return (Criteria) this;
        }

        public Criteria andUPasswordBetween(String value1, String value2) {
            addCriterion("u_password between", value1, value2, "uPassword");
            return (Criteria) this;
        }

        public Criteria andUPasswordNotBetween(String value1, String value2) {
            addCriterion("u_password not between", value1, value2, "uPassword");
            return (Criteria) this;
        }

        public Criteria andUMailAddIsNull() {
            addCriterion("u_mail_add is null");
            return (Criteria) this;
        }

        public Criteria andUMailAddIsNotNull() {
            addCriterion("u_mail_add is not null");
            return (Criteria) this;
        }

        public Criteria andUMailAddEqualTo(String value) {
            addCriterion("u_mail_add =", value, "uMailAdd");
            return (Criteria) this;
        }

        public Criteria andUMailAddNotEqualTo(String value) {
            addCriterion("u_mail_add <>", value, "uMailAdd");
            return (Criteria) this;
        }

        public Criteria andUMailAddGreaterThan(String value) {
            addCriterion("u_mail_add >", value, "uMailAdd");
            return (Criteria) this;
        }

        public Criteria andUMailAddGreaterThanOrEqualTo(String value) {
            addCriterion("u_mail_add >=", value, "uMailAdd");
            return (Criteria) this;
        }

        public Criteria andUMailAddLessThan(String value) {
            addCriterion("u_mail_add <", value, "uMailAdd");
            return (Criteria) this;
        }

        public Criteria andUMailAddLessThanOrEqualTo(String value) {
            addCriterion("u_mail_add <=", value, "uMailAdd");
            return (Criteria) this;
        }

        public Criteria andUMailAddLike(String value) {
            addCriterion("u_mail_add like", value, "uMailAdd");
            return (Criteria) this;
        }

        public Criteria andUMailAddNotLike(String value) {
            addCriterion("u_mail_add not like", value, "uMailAdd");
            return (Criteria) this;
        }

        public Criteria andUMailAddIn(List<String> values) {
            addCriterion("u_mail_add in", values, "uMailAdd");
            return (Criteria) this;
        }

        public Criteria andUMailAddNotIn(List<String> values) {
            addCriterion("u_mail_add not in", values, "uMailAdd");
            return (Criteria) this;
        }

        public Criteria andUMailAddBetween(String value1, String value2) {
            addCriterion("u_mail_add between", value1, value2, "uMailAdd");
            return (Criteria) this;
        }

        public Criteria andUMailAddNotBetween(String value1, String value2) {
            addCriterion("u_mail_add not between", value1, value2, "uMailAdd");
            return (Criteria) this;
        }

        public Criteria andUAuthNoIsNull() {
            addCriterion("u_auth_no is null");
            return (Criteria) this;
        }

        public Criteria andUAuthNoIsNotNull() {
            addCriterion("u_auth_no is not null");
            return (Criteria) this;
        }

        public Criteria andUAuthNoEqualTo(Integer value) {
            addCriterion("u_auth_no =", value, "uAuthNo");
            return (Criteria) this;
        }

        public Criteria andUAuthNoNotEqualTo(Integer value) {
            addCriterion("u_auth_no <>", value, "uAuthNo");
            return (Criteria) this;
        }

        public Criteria andUAuthNoGreaterThan(Integer value) {
            addCriterion("u_auth_no >", value, "uAuthNo");
            return (Criteria) this;
        }

        public Criteria andUAuthNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("u_auth_no >=", value, "uAuthNo");
            return (Criteria) this;
        }

        public Criteria andUAuthNoLessThan(Integer value) {
            addCriterion("u_auth_no <", value, "uAuthNo");
            return (Criteria) this;
        }

        public Criteria andUAuthNoLessThanOrEqualTo(Integer value) {
            addCriterion("u_auth_no <=", value, "uAuthNo");
            return (Criteria) this;
        }

        public Criteria andUAuthNoIn(List<Integer> values) {
            addCriterion("u_auth_no in", values, "uAuthNo");
            return (Criteria) this;
        }

        public Criteria andUAuthNoNotIn(List<Integer> values) {
            addCriterion("u_auth_no not in", values, "uAuthNo");
            return (Criteria) this;
        }

        public Criteria andUAuthNoBetween(Integer value1, Integer value2) {
            addCriterion("u_auth_no between", value1, value2, "uAuthNo");
            return (Criteria) this;
        }

        public Criteria andUAuthNoNotBetween(Integer value1, Integer value2) {
            addCriterion("u_auth_no not between", value1, value2, "uAuthNo");
            return (Criteria) this;
        }

        public Criteria andUProfileIsNull() {
            addCriterion("u_profile is null");
            return (Criteria) this;
        }

        public Criteria andUProfileIsNotNull() {
            addCriterion("u_profile is not null");
            return (Criteria) this;
        }

        public Criteria andUProfileEqualTo(String value) {
            addCriterion("u_profile =", value, "uProfile");
            return (Criteria) this;
        }

        public Criteria andUProfileNotEqualTo(String value) {
            addCriterion("u_profile <>", value, "uProfile");
            return (Criteria) this;
        }

        public Criteria andUProfileGreaterThan(String value) {
            addCriterion("u_profile >", value, "uProfile");
            return (Criteria) this;
        }

        public Criteria andUProfileGreaterThanOrEqualTo(String value) {
            addCriterion("u_profile >=", value, "uProfile");
            return (Criteria) this;
        }

        public Criteria andUProfileLessThan(String value) {
            addCriterion("u_profile <", value, "uProfile");
            return (Criteria) this;
        }

        public Criteria andUProfileLessThanOrEqualTo(String value) {
            addCriterion("u_profile <=", value, "uProfile");
            return (Criteria) this;
        }

        public Criteria andUProfileLike(String value) {
            addCriterion("u_profile like", value, "uProfile");
            return (Criteria) this;
        }

        public Criteria andUProfileNotLike(String value) {
            addCriterion("u_profile not like", value, "uProfile");
            return (Criteria) this;
        }

        public Criteria andUProfileIn(List<String> values) {
            addCriterion("u_profile in", values, "uProfile");
            return (Criteria) this;
        }

        public Criteria andUProfileNotIn(List<String> values) {
            addCriterion("u_profile not in", values, "uProfile");
            return (Criteria) this;
        }

        public Criteria andUProfileBetween(String value1, String value2) {
            addCriterion("u_profile between", value1, value2, "uProfile");
            return (Criteria) this;
        }

        public Criteria andUProfileNotBetween(String value1, String value2) {
            addCriterion("u_profile not between", value1, value2, "uProfile");
            return (Criteria) this;
        }

        public Criteria andUProfilePhotoNameIsNull() {
            addCriterion("u_profile_photo_name is null");
            return (Criteria) this;
        }

        public Criteria andUProfilePhotoNameIsNotNull() {
            addCriterion("u_profile_photo_name is not null");
            return (Criteria) this;
        }

        public Criteria andUProfilePhotoNameEqualTo(String value) {
            addCriterion("u_profile_photo_name =", value, "uProfilePhotoName");
            return (Criteria) this;
        }

        public Criteria andUProfilePhotoNameNotEqualTo(String value) {
            addCriterion("u_profile_photo_name <>", value, "uProfilePhotoName");
            return (Criteria) this;
        }

        public Criteria andUProfilePhotoNameGreaterThan(String value) {
            addCriterion("u_profile_photo_name >", value, "uProfilePhotoName");
            return (Criteria) this;
        }

        public Criteria andUProfilePhotoNameGreaterThanOrEqualTo(String value) {
            addCriterion("u_profile_photo_name >=", value, "uProfilePhotoName");
            return (Criteria) this;
        }

        public Criteria andUProfilePhotoNameLessThan(String value) {
            addCriterion("u_profile_photo_name <", value, "uProfilePhotoName");
            return (Criteria) this;
        }

        public Criteria andUProfilePhotoNameLessThanOrEqualTo(String value) {
            addCriterion("u_profile_photo_name <=", value, "uProfilePhotoName");
            return (Criteria) this;
        }

        public Criteria andUProfilePhotoNameLike(String value) {
            addCriterion("u_profile_photo_name like", value, "uProfilePhotoName");
            return (Criteria) this;
        }

        public Criteria andUProfilePhotoNameNotLike(String value) {
            addCriterion("u_profile_photo_name not like", value, "uProfilePhotoName");
            return (Criteria) this;
        }

        public Criteria andUProfilePhotoNameIn(List<String> values) {
            addCriterion("u_profile_photo_name in", values, "uProfilePhotoName");
            return (Criteria) this;
        }

        public Criteria andUProfilePhotoNameNotIn(List<String> values) {
            addCriterion("u_profile_photo_name not in", values, "uProfilePhotoName");
            return (Criteria) this;
        }

        public Criteria andUProfilePhotoNameBetween(String value1, String value2) {
            addCriterion("u_profile_photo_name between", value1, value2, "uProfilePhotoName");
            return (Criteria) this;
        }

        public Criteria andUProfilePhotoNameNotBetween(String value1, String value2) {
            addCriterion("u_profile_photo_name not between", value1, value2, "uProfilePhotoName");
            return (Criteria) this;
        }

        public Criteria andUProfileBackgroundimgNameIsNull() {
            addCriterion("u_profile_backgroundimg_name is null");
            return (Criteria) this;
        }

        public Criteria andUProfileBackgroundimgNameIsNotNull() {
            addCriterion("u_profile_backgroundimg_name is not null");
            return (Criteria) this;
        }

        public Criteria andUProfileBackgroundimgNameEqualTo(String value) {
            addCriterion("u_profile_backgroundimg_name =", value, "uProfileBackgroundimgName");
            return (Criteria) this;
        }

        public Criteria andUProfileBackgroundimgNameNotEqualTo(String value) {
            addCriterion("u_profile_backgroundimg_name <>", value, "uProfileBackgroundimgName");
            return (Criteria) this;
        }

        public Criteria andUProfileBackgroundimgNameGreaterThan(String value) {
            addCriterion("u_profile_backgroundimg_name >", value, "uProfileBackgroundimgName");
            return (Criteria) this;
        }

        public Criteria andUProfileBackgroundimgNameGreaterThanOrEqualTo(String value) {
            addCriterion("u_profile_backgroundimg_name >=", value, "uProfileBackgroundimgName");
            return (Criteria) this;
        }

        public Criteria andUProfileBackgroundimgNameLessThan(String value) {
            addCriterion("u_profile_backgroundimg_name <", value, "uProfileBackgroundimgName");
            return (Criteria) this;
        }

        public Criteria andUProfileBackgroundimgNameLessThanOrEqualTo(String value) {
            addCriterion("u_profile_backgroundimg_name <=", value, "uProfileBackgroundimgName");
            return (Criteria) this;
        }

        public Criteria andUProfileBackgroundimgNameLike(String value) {
            addCriterion("u_profile_backgroundimg_name like", value, "uProfileBackgroundimgName");
            return (Criteria) this;
        }

        public Criteria andUProfileBackgroundimgNameNotLike(String value) {
            addCriterion("u_profile_backgroundimg_name not like", value, "uProfileBackgroundimgName");
            return (Criteria) this;
        }

        public Criteria andUProfileBackgroundimgNameIn(List<String> values) {
            addCriterion("u_profile_backgroundimg_name in", values, "uProfileBackgroundimgName");
            return (Criteria) this;
        }

        public Criteria andUProfileBackgroundimgNameNotIn(List<String> values) {
            addCriterion("u_profile_backgroundimg_name not in", values, "uProfileBackgroundimgName");
            return (Criteria) this;
        }

        public Criteria andUProfileBackgroundimgNameBetween(String value1, String value2) {
            addCriterion("u_profile_backgroundimg_name between", value1, value2, "uProfileBackgroundimgName");
            return (Criteria) this;
        }

        public Criteria andUProfileBackgroundimgNameNotBetween(String value1, String value2) {
            addCriterion("u_profile_backgroundimg_name not between", value1, value2, "uProfileBackgroundimgName");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}