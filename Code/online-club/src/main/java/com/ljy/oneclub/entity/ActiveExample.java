package com.ljy.oneclub.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActiveExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ActiveExample() {
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

        public Criteria andActiveIdIsNull() {
            addCriterion("active_id is null");
            return (Criteria) this;
        }

        public Criteria andActiveIdIsNotNull() {
            addCriterion("active_id is not null");
            return (Criteria) this;
        }

        public Criteria andActiveIdEqualTo(Integer value) {
            addCriterion("active_id =", value, "activeId");
            return (Criteria) this;
        }

        public Criteria andActiveIdNotEqualTo(Integer value) {
            addCriterion("active_id <>", value, "activeId");
            return (Criteria) this;
        }

        public Criteria andActiveIdGreaterThan(Integer value) {
            addCriterion("active_id >", value, "activeId");
            return (Criteria) this;
        }

        public Criteria andActiveIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("active_id >=", value, "activeId");
            return (Criteria) this;
        }

        public Criteria andActiveIdLessThan(Integer value) {
            addCriterion("active_id <", value, "activeId");
            return (Criteria) this;
        }

        public Criteria andActiveIdLessThanOrEqualTo(Integer value) {
            addCriterion("active_id <=", value, "activeId");
            return (Criteria) this;
        }

        public Criteria andActiveIdIn(List<Integer> values) {
            addCriterion("active_id in", values, "activeId");
            return (Criteria) this;
        }

        public Criteria andActiveIdNotIn(List<Integer> values) {
            addCriterion("active_id not in", values, "activeId");
            return (Criteria) this;
        }

        public Criteria andActiveIdBetween(Integer value1, Integer value2) {
            addCriterion("active_id between", value1, value2, "activeId");
            return (Criteria) this;
        }

        public Criteria andActiveIdNotBetween(Integer value1, Integer value2) {
            addCriterion("active_id not between", value1, value2, "activeId");
            return (Criteria) this;
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

        public Criteria andActiveTypeIsNull() {
            addCriterion("active_type is null");
            return (Criteria) this;
        }

        public Criteria andActiveTypeIsNotNull() {
            addCriterion("active_type is not null");
            return (Criteria) this;
        }

        public Criteria andActiveTypeEqualTo(Integer value) {
            addCriterion("active_type =", value, "activeType");
            return (Criteria) this;
        }

        public Criteria andActiveTypeNotEqualTo(Integer value) {
            addCriterion("active_type <>", value, "activeType");
            return (Criteria) this;
        }

        public Criteria andActiveTypeGreaterThan(Integer value) {
            addCriterion("active_type >", value, "activeType");
            return (Criteria) this;
        }

        public Criteria andActiveTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("active_type >=", value, "activeType");
            return (Criteria) this;
        }

        public Criteria andActiveTypeLessThan(Integer value) {
            addCriterion("active_type <", value, "activeType");
            return (Criteria) this;
        }

        public Criteria andActiveTypeLessThanOrEqualTo(Integer value) {
            addCriterion("active_type <=", value, "activeType");
            return (Criteria) this;
        }

        public Criteria andActiveTypeIn(List<Integer> values) {
            addCriterion("active_type in", values, "activeType");
            return (Criteria) this;
        }

        public Criteria andActiveTypeNotIn(List<Integer> values) {
            addCriterion("active_type not in", values, "activeType");
            return (Criteria) this;
        }

        public Criteria andActiveTypeBetween(Integer value1, Integer value2) {
            addCriterion("active_type between", value1, value2, "activeType");
            return (Criteria) this;
        }

        public Criteria andActiveTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("active_type not between", value1, value2, "activeType");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andViewedCountIsNull() {
            addCriterion("viewed_count is null");
            return (Criteria) this;
        }

        public Criteria andViewedCountIsNotNull() {
            addCriterion("viewed_count is not null");
            return (Criteria) this;
        }

        public Criteria andViewedCountEqualTo(Integer value) {
            addCriterion("viewed_count =", value, "viewedCount");
            return (Criteria) this;
        }

        public Criteria andViewedCountNotEqualTo(Integer value) {
            addCriterion("viewed_count <>", value, "viewedCount");
            return (Criteria) this;
        }

        public Criteria andViewedCountGreaterThan(Integer value) {
            addCriterion("viewed_count >", value, "viewedCount");
            return (Criteria) this;
        }

        public Criteria andViewedCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("viewed_count >=", value, "viewedCount");
            return (Criteria) this;
        }

        public Criteria andViewedCountLessThan(Integer value) {
            addCriterion("viewed_count <", value, "viewedCount");
            return (Criteria) this;
        }

        public Criteria andViewedCountLessThanOrEqualTo(Integer value) {
            addCriterion("viewed_count <=", value, "viewedCount");
            return (Criteria) this;
        }

        public Criteria andViewedCountIn(List<Integer> values) {
            addCriterion("viewed_count in", values, "viewedCount");
            return (Criteria) this;
        }

        public Criteria andViewedCountNotIn(List<Integer> values) {
            addCriterion("viewed_count not in", values, "viewedCount");
            return (Criteria) this;
        }

        public Criteria andViewedCountBetween(Integer value1, Integer value2) {
            addCriterion("viewed_count between", value1, value2, "viewedCount");
            return (Criteria) this;
        }

        public Criteria andViewedCountNotBetween(Integer value1, Integer value2) {
            addCriterion("viewed_count not between", value1, value2, "viewedCount");
            return (Criteria) this;
        }

        public Criteria andActiveTitleIsNull() {
            addCriterion("active_title is null");
            return (Criteria) this;
        }

        public Criteria andActiveTitleIsNotNull() {
            addCriterion("active_title is not null");
            return (Criteria) this;
        }

        public Criteria andActiveTitleEqualTo(String value) {
            addCriterion("active_title =", value, "activeTitle");
            return (Criteria) this;
        }

        public Criteria andActiveTitleNotEqualTo(String value) {
            addCriterion("active_title <>", value, "activeTitle");
            return (Criteria) this;
        }

        public Criteria andActiveTitleGreaterThan(String value) {
            addCriterion("active_title >", value, "activeTitle");
            return (Criteria) this;
        }

        public Criteria andActiveTitleGreaterThanOrEqualTo(String value) {
            addCriterion("active_title >=", value, "activeTitle");
            return (Criteria) this;
        }

        public Criteria andActiveTitleLessThan(String value) {
            addCriterion("active_title <", value, "activeTitle");
            return (Criteria) this;
        }

        public Criteria andActiveTitleLessThanOrEqualTo(String value) {
            addCriterion("active_title <=", value, "activeTitle");
            return (Criteria) this;
        }

        public Criteria andActiveTitleLike(String value) {
            addCriterion("active_title like", value, "activeTitle");
            return (Criteria) this;
        }

        public Criteria andActiveTitleNotLike(String value) {
            addCriterion("active_title not like", value, "activeTitle");
            return (Criteria) this;
        }

        public Criteria andActiveTitleIn(List<String> values) {
            addCriterion("active_title in", values, "activeTitle");
            return (Criteria) this;
        }

        public Criteria andActiveTitleNotIn(List<String> values) {
            addCriterion("active_title not in", values, "activeTitle");
            return (Criteria) this;
        }

        public Criteria andActiveTitleBetween(String value1, String value2) {
            addCriterion("active_title between", value1, value2, "activeTitle");
            return (Criteria) this;
        }

        public Criteria andActiveTitleNotBetween(String value1, String value2) {
            addCriterion("active_title not between", value1, value2, "activeTitle");
            return (Criteria) this;
        }

        public Criteria andLikedIsNull() {
            addCriterion("liked is null");
            return (Criteria) this;
        }

        public Criteria andLikedIsNotNull() {
            addCriterion("liked is not null");
            return (Criteria) this;
        }

        public Criteria andLikedEqualTo(Integer value) {
            addCriterion("liked =", value, "liked");
            return (Criteria) this;
        }

        public Criteria andLikedNotEqualTo(Integer value) {
            addCriterion("liked <>", value, "liked");
            return (Criteria) this;
        }

        public Criteria andLikedGreaterThan(Integer value) {
            addCriterion("liked >", value, "liked");
            return (Criteria) this;
        }

        public Criteria andLikedGreaterThanOrEqualTo(Integer value) {
            addCriterion("liked >=", value, "liked");
            return (Criteria) this;
        }

        public Criteria andLikedLessThan(Integer value) {
            addCriterion("liked <", value, "liked");
            return (Criteria) this;
        }

        public Criteria andLikedLessThanOrEqualTo(Integer value) {
            addCriterion("liked <=", value, "liked");
            return (Criteria) this;
        }

        public Criteria andLikedIn(List<Integer> values) {
            addCriterion("liked in", values, "liked");
            return (Criteria) this;
        }

        public Criteria andLikedNotIn(List<Integer> values) {
            addCriterion("liked not in", values, "liked");
            return (Criteria) this;
        }

        public Criteria andLikedBetween(Integer value1, Integer value2) {
            addCriterion("liked between", value1, value2, "liked");
            return (Criteria) this;
        }

        public Criteria andLikedNotBetween(Integer value1, Integer value2) {
            addCriterion("liked not between", value1, value2, "liked");
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