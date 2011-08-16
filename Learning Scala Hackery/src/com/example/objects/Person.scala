package com.example.objects

/**
 * Person class
 */
class Person(var firstName: String, var lastName: String, var age: Int) {

  def getFirstName = 
    firstName
    
  def getLastName = 
    lastName
    
  def getAge = 
    age

  def setFirstName(value: String): Unit = 
    firstName = value
    
  def setLastName(value: String): Unit = 
    lastName = value
    
  def setAge(value: Int): Unit = 
    age = value

  override def toString =
    "[Person firstName:" + firstName + " lastName:" + lastName + " age:" + age + " ]"
}

object RunPerson {
  
  /**
   * Main method
   */
  def main(args: Array[String]) {

    var myPerson = new Person("Anthony", "Kilman", 27)
    Console.println(myPerson)

  }
}