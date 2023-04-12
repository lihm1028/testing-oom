package testing.oom.memory;

import org.openjdk.jol.info.ClassLayout;

public class Test {
    public static void main(String[] args) {

//        Integer i = new Integer(23);
//        int ib=23;
//        long l=23;
        Long ll=new Long(24);
//        boolean success=false;
//        System.out.println(ClassLayout.parseInstance(success).toPrintable());
//        System.out.println(ClassLayout.parseInstance(ib).toPrintable());
//        System.out.println(ClassLayout.parseInstance(l).toPrintable());
        System.out.println(ClassLayout.parseInstance(ll).toPrintable());
//
//        Dog dog1 = new Dog("dog1");
//
//        Dog dog2 = new Dog("dog2");
//
//        User user = new User(32, dog1, dog2);
//
//        System.out.println(ClassLayout.parseInstance(user).toPrintable());
//        List<Object> dogList = new ArrayList<>();
//
//        System.out.println(ClassLayout.parseInstance(dogList).toPrintable());
//
        int[] houses = new int[10];
        System.out.println(ClassLayout.parseInstance(houses).toPrintable());
//
//
//        System.out.println("user hashcode=" + user.hashCode());
//        System.out.println(ClassLayout.parseInstance(user).toPrintable());


        /**
         *      L0
         *     LINENUMBER 5 L0
         *     NEW testing/oom/memory/Dog
         *     DUP
         *     LDC "dog1"
         *     INVOKESPECIAL testing/oom/memory/Dog.<init> (Ljava/lang/String;)V
         *     ASTORE 1
         *    L1
         *     LINENUMBER 41 L1
         *     RETURN
         *    L2
         *     LOCALVARIABLE args [Ljava/lang/String; L0 L2 0
         *     LOCALVARIABLE dog1 Ltesting/oom/memory/Dog; L1 L2 1
         *     MAXSTACK = 3
         *     MAXLOCALS = 2
         */
    }
}
