package com.test.it.tomcat.exp20.modelbeantest;

import javax.management.*;
import javax.management.modelmbean.*;

/**
 * Created by caizh on 2015/9/2 0002.
 */
public class ModelAgent {
    private String MANAGED_CLASS_NAME = "com.test.it.tomcat.exp20.modelbeantest.Car";

    private MBeanServer mBeanServer = null;

    public ModelAgent() {
        mBeanServer = MBeanServerFactory.createMBeanServer();
    }

    public MBeanServer getmBeanServer() {
        return mBeanServer;
    }

    public void setmBeanServer(MBeanServer mBeanServer) {
        this.mBeanServer = mBeanServer;
    }

    private ObjectName createObjectName(String name) {
        ObjectName objectName = null;
        try {
            objectName = new ObjectName(name);
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        }
        return objectName;
    }

    private ModelMBean createMBean(ObjectName objectName, String mbeanName) {
        ModelMBeanInfo mBeanInfo = createModelMBeanInfo(objectName, mbeanName);
        RequiredModelMBean modelMBean = null;
        try {
            modelMBean = new RequiredModelMBean(mBeanInfo);
        } catch (MBeanException e) {
            e.printStackTrace();
        }
        return modelMBean;
    }

    private ModelMBeanInfo createModelMBeanInfo(ObjectName objectName, String mbeanName) {
        ModelMBeanInfo mBeanInfo = null;
        ModelMBeanAttributeInfo[] attributes = new ModelMBeanAttributeInfo[1];
        ModelMBeanOperationInfo[] operations = new ModelMBeanOperationInfo[3];
        attributes[0] = new ModelMBeanAttributeInfo("color", "java.lang.String", "car's color", true, true, false, null);
        operations[0] = new ModelMBeanOperationInfo("drive", "the drive method", null, "void", MBeanOperationInfo.ACTION);
        operations[0] = new ModelMBeanOperationInfo("drive", "the drive method", null, "void", MBeanOperationInfo.ACTION, null);
        operations[1] = new ModelMBeanOperationInfo("getColor", "get color attribute", null, "java.lang.String", MBeanOperationInfo.ACTION, null);

        Descriptor setColorDesc = new DescriptorSupport(new String[]{"name=setColor",
                "descriptorType=operation", "class=" + MANAGED_CLASS_NAME + "role=operation"});
        MBeanParameterInfo[] setColorParams = new MBeanParameterInfo[]{new MBeanParameterInfo("new color", "java.lang.String", "new color value")};
        operations[2] = new ModelMBeanOperationInfo("setColor", "set color", setColorParams, "void", MBeanOperationInfo.ACTION, setColorDesc);

        mBeanInfo = new ModelMBeanInfoSupport(MANAGED_CLASS_NAME, null, attributes, null, operations, null);

        return mBeanInfo;
    }

    public static void main(String[] args) {
        ModelAgent agent = new ModelAgent();
        MBeanServer mBeanServer = agent.getmBeanServer();
        Car car = new Car();
        String domain = mBeanServer.getDefaultDomain();
        ObjectName objectName = agent.createObjectName(domain + ":type=MyCar");
        String mBeanName = "myMBean";

        ModelMBean modelMBean = agent.createMBean(objectName, mBeanName);

        try {
            modelMBean.setManagedResource(car, "ObjectReference");
            mBeanServer.registerMBean(modelMBean, objectName);
        } catch (MBeanException e) {
            e.printStackTrace();
        } catch (InstanceNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidTargetObjectTypeException e) {
            e.printStackTrace();
        } catch (NotCompliantMBeanException e) {
            e.printStackTrace();
        } catch (InstanceAlreadyExistsException e) {
            e.printStackTrace();
        }

        try {
            Attribute attribute = new Attribute("color", "blue");
            mBeanServer.setAttribute(objectName, attribute);

            String color = (String) mBeanServer.getAttribute(objectName, "color");
            System.out.println("color: " + color);

            attribute = new Attribute("color", "rd");
            mBeanServer.setAttribute(objectName, attribute);

            color = (String) mBeanServer.getAttribute(objectName, "color");
            System.out.println("color: " + color);

            mBeanServer.invoke(objectName, "drive", null, null);

        } catch (InstanceNotFoundException e) {
            e.printStackTrace();
        } catch (AttributeNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidAttributeValueException e) {
            e.printStackTrace();
        } catch (MBeanException e) {
            e.printStackTrace();
        } catch (ReflectionException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(60000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
