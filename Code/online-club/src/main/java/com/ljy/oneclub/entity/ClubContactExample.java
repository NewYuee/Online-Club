package com.ljy.oneclub.entity;

import java.util.ArrayList;
import java.util.List;

public class ClubContactExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ClubContactExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andClubIdIsNull() {
            addCriterion("club_id is null");
            return (Criteria) this;
        }

        public Criteria andClubIdIsNotNull() {
            addCriterion("club_id is not null");
            return (Criteria) this;
        }

        public Criteria andClubIdEqualTo(Integer value) {
            addCriterion("club_id =", value, "clubId");
            return (Criteria) this;
        }

        public Criteria andClubIdNotEqualTo(Integer value) {
            addCriterion("club_id <>", value, "clubId");
            return (Criteria) this;
        }

        public Criteria andClubIdGreaterThan(Integer value) {
            addCriterion("club_id >", value, "clubId");
            return (Criteria) this;
        }

        public Criteria andClubIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("club_id >=", value, "clubId");
            return (Criteria) this;
        }

        public Criteria andClubIdLessThan(Integer value) {
            addCriterion("club_id <", value, "clubId");
            return (Criteria) this;
        }

        public Criteria andClubIdLessThanOrEqualTo(Integer value) {
            addCriterion("club_id <=", value, "clubId");
            return (Criteria) this;
        }

        public Criteria andClubIdIn(List<Integer> values) {
            addCriterion("club_id in", values, "clubId");
            return (Criteria) this;
        }

        public Criteria andClubIdNotIn(List<Integer> values) {
            addCriterion("club_id not in", values, "clubId");
            return (Criteria) this;
        }

        public Criteria andClubIdBetween(Integer value1, Integer value2) {
            addCriterion("club_id between", value1, value2, "clubId");
            return (Criteria) this;
        }

        public Criteria andClubIdNotBetween(Integer value1, Integer value2) {
            addCriterion("club_id not between", value1, value2, "clubId");
            return (Criteria) this;
        }

        public Criteria andRealNameIsNull() {
            addCriterion("real_name is null");
            return (Criteria) this;
        }

        public Criteria andRealNameIsNotNull() {
            addCriterion("real_name is not null");
            return (Criteria) this;
        }

        public Criteria andRealNameEqualTo(String value) {
            addCriterion("real_name =", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotEqualTo(String value) {
            addCriterion("real_name <>", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameGreaterThan(String value) {
            addCriterion("real_name >", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameGreaterThanOrEqualTo(String value) {
            addCriterion("real_name >=", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameLessThan(String value) {
            addCriterion("real_name <", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameLessThanOrEqualTo(String value) {
            addCriterion("real_name <=", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameLike(String value) {
            addCriterion("real_name like", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotLike(String value) {
            addCriterion("real_name not like", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameIn(List<String> values) {
            addCriterion("real_name in", values, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotIn(List<String> values) {
            addCriterion("real_name not in", values, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameBetween(String value1, String value2) {
            addCriterion("real_name between", value1, value2, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotBetween(String value1, String value2) {
            addCriterion("real_name not between", value1, value2, "realName");
            return (Criteria) this;
        }

        public Criteria andTelNumIsNull() {
            addCriterion("tel_num is null");
            return (Criteria) this;
        }

        public Criteria andTelNumIsNotNull() {
            addCriterion("tel_num is not null");
            return (Criteria) this;
        }

        public Criteria andTelNumEqualTo(String value) {
            addCriterion("tel_num =", value, "telNum");
            return (Criteria) this;
        }

        public Criteria andTelNumNotEqualTo(String value) {
            addCriterion("tel_num <>", value, "telNum");
            return (Criteria) this;
        }

        public Criteria andTelNumGreaterThan(String value) {
            addCriterion("tel_num >", value, "telNum");
            return (Criteria) this;
        }

        public Criteria andTelNumGreaterThanOrEqualTo(String value) {
            addCriterion("tel_num >=", value, "telNum");
            return (Criteria) this;
        }

        public Criteria andTelNumLessThan(String value) {
            addCriterion("tel_num <", value, "telNum");
            return (Criteria) this;
        }

        public Criteria andTelNumLessThanOrEqualTo(String value) {
            addCriterion("tel_num <=", value, "telNum");
            return (Criteria) this;
        }

        public Criteria andTelNumLike(String value) {
            addCriterion("tel_num like", value, "telNum");
            return (Criteria) this;
        }

        public Criteria andTelNumNotLike(String value) {
            addCriterion("tel_num not like", value, "telNum");
            return (Criteria) this;
        }

        public Criteria andTelNumIn(List<String> values) {
            addCriterion("tel_num in", values, "telNum");
            return (Criteria) this;
        }

        public Criteria andTelNumNotIn(List<String> values) {
            addCriterion("tel_num not in", values, "telNum");
            return (Criteria) this;
        }

        public Criteria andTelNumBetween(String value1, String value2) {
            addCriterion("tel_num between", value1, value2, "telNum");
            return (Criteria) this;
        }

        public Criteria andTelNumNotBetween(String value1, String value2) {
            addCriterion("tel_num not between", value1, value2, "telNum");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andDepartmentInClubIsNull() {
            addCriterion("department_in_club is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentInClubIsNotNull() {
            addCriterion("department_in_club is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentInClubEqualTo(String value) {
            addCriterion("department_in_club =", value, "departmentInClub");
            return (Criteria) this;
        }

        public Criteria andDepartmentInClubNotEqualTo(String value) {
            addCriterion("department_in_club <>", value, "departmentInClub");
            return (Criteria) this;
        }

        public Criteria andDepartmentInClubGreaterThan(String value) {
            addCriterion("department_in_club >", value, "departmentInClub");
            return (Criteria) this;
        }

        public Criteria andDepartmentInClubGreaterThanOrEqualTo(String value) {
            addCriterion("department_in_club >=", value, "departmentInClub");
            return (Criteria) this;
        }

        public Criteria andDepartmentInClubLessThan(String value) {
            addCriterion("department_in_club <", value, "departmentInClub");
            return (Criteria) this;
        }

        public Criteria andDepartmentInClubLessThanOrEqualTo(String value) {
            addCriterion("department_in_club <=", value, "departmentInClub");
            return (Criteria) this;
        }

        public Criteria andDepartmentInClubLike(String value) {
            addCriterion("department_in_club like", value, "departmentInClub");
            return (Criteria) this;
        }

        public Criteria andDepartmentInClubNotLike(String value) {
            addCriterion("department_in_club not like", value, "departmentInClub");
            return (Criteria) this;
        }

        public Criteria andDepartmentInClubIn(List<String> values) {
            addCriterion("department_in_club in", values, "departmentInClub");
            return (Criteria) this;
        }

        public Criteria andDepartmentInClubNotIn(List<String> values) {
            addCriterion("department_in_club not in", values, "departmentInClub");
            return (Criteria) this;
        }

        public Criteria andDepartmentInClubBetween(String value1, String value2) {
            addCriterion("department_in_club between", value1, value2, "departmentInClub");
            return (Criteria) this;
        }

        public Criteria andDepartmentInClubNotBetween(String value1, String value2) {
            addCriterion("department_in_club not between", value1, value2, "departmentInClub");
            return (Criteria) this;
        }

        public Criteria andPositionInClubIsNull() {
            addCriterion("position_in_club is null");
            return (Criteria) this;
        }

        public Criteria andPositionInClubIsNotNull() {
            addCriterion("position_in_club is not null");
            return (Criteria) this;
        }

        public Criteria andPositionInClubEqualTo(String value) {
            addCriterion("position_in_club =", value, "positionInClub");
            return (Criteria) this;
        }

        public Criteria andPositionInClubNotEqualTo(String value) {
            addCriterion("position_in_club <>", value, "positionInClub");
            return (Criteria) this;
        }

        public Criteria andPositionInClubGreaterThan(String value) {
            addCriterion("position_in_club >", value, "positionInClub");
            return (Criteria) this;
        }

        public Criteria andPositionInClubGreaterThanOrEqualTo(String value) {
            addCriterion("position_in_club >=", value, "positionInClub");
            return (Criteria) this;
        }

        public Criteria andPositionInClubLessThan(String value) {
            addCriterion("position_in_club <", value, "positionInClub");
            return (Criteria) this;
        }

        public Criteria andPositionInClubLessThanOrEqualTo(String value) {
            addCriterion("position_in_club <=", value, "positionInClub");
            return (Criteria) this;
        }

        public Criteria andPositionInClubLike(String value) {
            addCriterion("position_in_club like", value, "positionInClub");
            return (Criteria) this;
        }

        public Criteria andPositionInClubNotLike(String value) {
            addCriterion("position_in_club not like", value, "positionInClub");
            return (Criteria) this;
        }

        public Criteria andPositionInClubIn(List<String> values) {
            addCriterion("position_in_club in", values, "positionInClub");
            return (Criteria) this;
        }

        public Criteria andPositionInClubNotIn(List<String> values) {
            addCriterion("position_in_club not in", values, "positionInClub");
            return (Criteria) this;
        }

        public Criteria andPositionInClubBetween(String value1, String value2) {
            addCriterion("position_in_club between", value1, value2, "positionInClub");
            return (Criteria) this;
        }

        public Criteria andPositionInClubNotBetween(String value1, String value2) {
            addCriterion("position_in_club not between", value1, value2, "positionInClub");
            return (Criteria) this;
        }

        public Criteria andOtherIsNull() {
            addCriterion("other is null");
            return (Criteria) this;
        }

        public Criteria andOtherIsNotNull() {
            addCriterion("other is not null");
            return (Criteria) this;
        }

        public Criteria andOtherEqualTo(String value) {
            addCriterion("other =", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherNotEqualTo(String value) {
            addCriterion("other <>", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherGreaterThan(String value) {
            addCriterion("other >", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherGreaterThanOrEqualTo(String value) {
            addCriterion("other >=", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherLessThan(String value) {
            addCriterion("other <", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherLessThanOrEqualTo(String value) {
            addCriterion("other <=", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherLike(String value) {
            addCriterion("other like", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherNotLike(String value) {
            addCriterion("other not like", value, "other");
            return (Criteria) this;
        }

        public Criteria andOtherIn(List<String> values) {
            addCriterion("other in", values, "other");
            return (Criteria) this;
        }

        public Criteria andOtherNotIn(List<String> values) {
            addCriterion("other not in", values, "other");
            return (Criteria) this;
        }

        public Criteria andOtherBetween(String value1, String value2) {
            addCriterion("other between", value1, value2, "other");
            return (Criteria) this;
        }

        public Criteria andOtherNotBetween(String value1, String value2) {
            addCriterion("other not between", value1, value2, "other");
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