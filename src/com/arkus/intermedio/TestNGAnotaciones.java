package com.arkus.intermedio;

import org.testng.annotations.*;

public class TestNGAnotaciones {
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("Esto se ejecuta antes de una suite de pruebas");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("Esto se ejecuta antes de las pruebas @Test");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("Esto se ejecuta antes la clase");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("Esto se ejecuta antes de cada metodo de prueba @Test");
    }

    @Test
    public void testCase1(){
        System.out.println("Clase de prueba 1");
    }

    @Test
    public void testCase2(){
        System.out.println("Clase de prueba 2");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("Esto se ejecuta despues de cada metodo de prueba");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("Esto se ejecuta despues de la clase");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("Esto se ejecuta despues de cada  de prueba @Test");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("Esto se ejecuta despues de una suite de pruebas");
    }
}
