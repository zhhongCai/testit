package com.test.it.mapstruct;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.test.it.mapstruct.dto.ProductInfo;
import com.test.it.mapstruct.model.Product;
import com.test.it.mapstruct.model.ProductSku;
import org.junit.Assert;
import org.junit.Test;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_SUPER;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.ASTORE;
import static org.objectweb.asm.Opcodes.DUP;
import static org.objectweb.asm.Opcodes.GETSTATIC;
import static org.objectweb.asm.Opcodes.ICONST_1;
import static org.objectweb.asm.Opcodes.ICONST_2;
import static org.objectweb.asm.Opcodes.INVOKEINTERFACE;
import static org.objectweb.asm.Opcodes.INVOKESPECIAL;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;
import static org.objectweb.asm.Opcodes.LCONST_1;
import static org.objectweb.asm.Opcodes.NEW;
import static org.objectweb.asm.Opcodes.POP;
import static org.objectweb.asm.Opcodes.RETURN;

/**
 * @Author: theonecai
 * @Date: Create in 2019-08-22 13:42
 * @Description:
 */
public class MapperTest {
    @Test
    public void test() throws Exception {
        Date now = new Date();
        Product product = new Product();
        product.setId(1L);
        product.setCreateTime(now);
        product.setDescription("description");
        product.setName("name");

        ProductSku sku = new ProductSku();
        Map<String, String> attributes = Maps.newHashMapWithExpectedSize(2);
        attributes.put("color", "red");
        attributes.put("size", "L");
        sku.setAttributes(attributes);
        sku.setCreateTime(now);
        sku.setSku("SKU001");
        sku.setId(2L);

        List<ProductSku> skus = Lists.newArrayListWithCapacity(1);
        skus.add(sku);
        product.setSkus(skus);

        ProductInfo productInfo = ProductMapper.INSTANCE.productToProductInfo(product);
        Assert.assertNotNull(productInfo);

        File file = new File("mapperTest.class");
        byte[] data = dump();
        if (!file.exists()) {
            file.createNewFile();
        }
        try (BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(file))) {
            bout.write(data);
            bout.flush();
        }
    }

    /**
     * 由bytecode outline生成
     * @return
     * @throws Exception
     */
    public static byte[] dump() throws Exception {

        ClassWriter cw = new ClassWriter(0);
        FieldVisitor fv;
        MethodVisitor mv;
        AnnotationVisitor av0;

        cw.visit(52, ACC_PUBLIC + ACC_SUPER, "com/test/it/mapstruct/MapperTest", null, "java/lang/Object", null);

        cw.visitSource("MapperTest.java", null);

        {
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(20, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitInsn(RETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "Lcom/test/it/mapstruct/MapperTest;", null, l0, l1, 0);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "test", "()V", null, null);
            {
                av0 = mv.visitAnnotation("Lorg/junit/Test;", true);
                av0.visitEnd();
            }
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(23, l0);
            mv.visitTypeInsn(NEW, "java/util/Date");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/util/Date", "<init>", "()V", false);
            mv.visitVarInsn(ASTORE, 1);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLineNumber(24, l1);
            mv.visitTypeInsn(NEW, "com/test/it/mapstruct/model/Product");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "com/test/it/mapstruct/model/Product", "<init>", "()V", false);
            mv.visitVarInsn(ASTORE, 2);
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLineNumber(25, l2);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitInsn(LCONST_1);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "com/test/it/mapstruct/model/Product", "setId", "(Ljava/lang/Long;)V", false);
            Label l3 = new Label();
            mv.visitLabel(l3);
            mv.visitLineNumber(26, l3);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "com/test/it/mapstruct/model/Product", "setCreateTime", "(Ljava/util/Date;)V", false);
            Label l4 = new Label();
            mv.visitLabel(l4);
            mv.visitLineNumber(27, l4);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitLdcInsn("description");
            mv.visitMethodInsn(INVOKEVIRTUAL, "com/test/it/mapstruct/model/Product", "setDescription", "(Ljava/lang/String;)V", false);
            Label l5 = new Label();
            mv.visitLabel(l5);
            mv.visitLineNumber(28, l5);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitLdcInsn("name");
            mv.visitMethodInsn(INVOKEVIRTUAL, "com/test/it/mapstruct/model/Product", "setName", "(Ljava/lang/String;)V", false);
            Label l6 = new Label();
            mv.visitLabel(l6);
            mv.visitLineNumber(30, l6);
            mv.visitTypeInsn(NEW, "com/test/it/mapstruct/model/ProductSku");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "com/test/it/mapstruct/model/ProductSku", "<init>", "()V", false);
            mv.visitVarInsn(ASTORE, 3);
            Label l7 = new Label();
            mv.visitLabel(l7);
            mv.visitLineNumber(31, l7);
            mv.visitInsn(ICONST_2);
            mv.visitMethodInsn(INVOKESTATIC, "com/google/common/collect/Maps", "newHashMapWithExpectedSize", "(I)Ljava/util/HashMap;", false);
            mv.visitVarInsn(ASTORE, 4);
            Label l8 = new Label();
            mv.visitLabel(l8);
            mv.visitLineNumber(32, l8);
            mv.visitVarInsn(ALOAD, 4);
            mv.visitLdcInsn("color");
            mv.visitLdcInsn("red");
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
            mv.visitInsn(POP);
            Label l9 = new Label();
            mv.visitLabel(l9);
            mv.visitLineNumber(33, l9);
            mv.visitVarInsn(ALOAD, 4);
            mv.visitLdcInsn("size");
            mv.visitLdcInsn("L");
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
            mv.visitInsn(POP);
            Label l10 = new Label();
            mv.visitLabel(l10);
            mv.visitLineNumber(34, l10);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitVarInsn(ALOAD, 4);
            mv.visitMethodInsn(INVOKEVIRTUAL, "com/test/it/mapstruct/model/ProductSku", "setAttributes", "(Ljava/util/Map;)V", false);
            Label l11 = new Label();
            mv.visitLabel(l11);
            mv.visitLineNumber(35, l11);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "com/test/it/mapstruct/model/ProductSku", "setCreateTime", "(Ljava/util/Date;)V", false);
            Label l12 = new Label();
            mv.visitLabel(l12);
            mv.visitLineNumber(36, l12);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitLdcInsn("SKU001");
            mv.visitMethodInsn(INVOKEVIRTUAL, "com/test/it/mapstruct/model/ProductSku", "setSku", "(Ljava/lang/String;)V", false);
            Label l13 = new Label();
            mv.visitLabel(l13);
            mv.visitLineNumber(37, l13);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitLdcInsn(new Long(2L));
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "com/test/it/mapstruct/model/ProductSku", "setId", "(Ljava/lang/Long;)V", false);
            Label l14 = new Label();
            mv.visitLabel(l14);
            mv.visitLineNumber(39, l14);
            mv.visitInsn(ICONST_1);
            mv.visitMethodInsn(INVOKESTATIC, "com/google/common/collect/Lists", "newArrayListWithCapacity", "(I)Ljava/util/ArrayList;", false);
            mv.visitVarInsn(ASTORE, 5);
            Label l15 = new Label();
            mv.visitLabel(l15);
            mv.visitLineNumber(40, l15);
            mv.visitVarInsn(ALOAD, 5);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true);
            mv.visitInsn(POP);
            Label l16 = new Label();
            mv.visitLabel(l16);
            mv.visitLineNumber(41, l16);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitVarInsn(ALOAD, 5);
            mv.visitMethodInsn(INVOKEVIRTUAL, "com/test/it/mapstruct/model/Product", "setSkus", "(Ljava/util/List;)V", false);
            Label l17 = new Label();
            mv.visitLabel(l17);
            mv.visitLineNumber(43, l17);
            mv.visitFieldInsn(GETSTATIC, "com/test/it/mapstruct/ProductMapper", "INSTANCE", "Lcom/test/it/mapstruct/ProductMapper;");
            mv.visitVarInsn(ALOAD, 2);
            mv.visitMethodInsn(INVOKEINTERFACE, "com/test/it/mapstruct/ProductMapper", "productToProductInfo", "(Lcom/test/it/mapstruct/model/Product;)Lcom/test/it/mapstruct/dto/ProductInfo;", true);
            mv.visitVarInsn(ASTORE, 6);
            Label l18 = new Label();
            mv.visitLabel(l18);
            mv.visitLineNumber(44, l18);
            mv.visitVarInsn(ALOAD, 6);
            mv.visitMethodInsn(INVOKESTATIC, "org/junit/Assert", "assertNotNull", "(Ljava/lang/Object;)V", false);
            Label l19 = new Label();
            mv.visitLabel(l19);
            mv.visitLineNumber(45, l19);
            mv.visitInsn(RETURN);
            Label l20 = new Label();
            mv.visitLabel(l20);
            mv.visitLocalVariable("this", "Lcom/test/it/mapstruct/MapperTest;", null, l0, l20, 0);
            mv.visitLocalVariable("now", "Ljava/util/Date;", null, l1, l20, 1);
            mv.visitLocalVariable("product", "Lcom/test/it/mapstruct/model/Product;", null, l2, l20, 2);
            mv.visitLocalVariable("sku", "Lcom/test/it/mapstruct/model/ProductSku;", null, l7, l20, 3);
            mv.visitLocalVariable("attributes", "Ljava/util/Map;", "Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;", l8, l20, 4);
            mv.visitLocalVariable("skus", "Ljava/util/List;", "Ljava/util/List<Lcom/test/it/mapstruct/model/ProductSku;>;", l15, l20, 5);
            mv.visitLocalVariable("productInfo", "Lcom/test/it/mapstruct/dto/ProductInfo;", null, l18, l20, 6);
            mv.visitMaxs(3, 7);
            mv.visitEnd();
        }
        cw.visitEnd();

        return cw.toByteArray();
    }
}
