package com.lab

import java.util.Date
import akka.actor.Actor
import akka.event.Logging

trait CustomerOperations {

  def getById(id: Long) = {
    OneCustomer(Customer(id, new Date(), "item1"))
  }

  def all() = {
    try {
      ListCustomers(List(Customer(100, new Date(), "item1")))
    } catch {
      case e: Exception => {
        println(e.getMessage())
        List()
      }
    }
  }

  def delete(id: Long) = {
    Success("deleted successfully")
  }

  def create(dueDate: Date, text: String) = {

    Created("created successfully")
  }

  def update(customer: Customer) = {
    getById(customer.id)
  }
}

class CustomerActor extends Actor with CustomerOperations {
  val log = Logging(context.system, this)

  def receive = {
    case GetCustomer(id) => sender ! getById(id)
    case UpdateCustomer(item) => sender ! update(item)
    case DeleteCustomer(id) => sender ! delete(id)
    case CreateCustomer(dueDate, text) => sender ! create(dueDate, text)
    case AllCustomers => sender ! all()
  }
}
