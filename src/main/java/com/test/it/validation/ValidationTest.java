package com.test.it.validation;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Author: caizh
 * CreateTime: 2015/5/19 16:31
 * Version: 1.0
 */
public class ValidationTest {
    public static void main(String[] args) {
        TestBean bean = new TestBean();
        bean.setName("a-");
        bean.setCode("aa");
        ValidatorFactory validationFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validationFactory.getValidator();
        Set<ConstraintViolation<TestBean>> result = validator.validate(bean);
        for (ConstraintViolation<TestBean> testBeanConstraintViolation : result) {
            System.out.println(testBeanConstraintViolation.getMessage());
        }
    }
}
