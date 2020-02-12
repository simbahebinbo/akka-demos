/**
 * return first name form your all name.
 * @param name
 * @returns {*}
 */
var firstName = function (name) {
  if (name == undefined) {
    return "";
  } else if (name == "") {
    return "";
  } else if (name.indexOf(" ") > 0) {
    return name.indexOf(" ")[0];
  } else {
    return name;
  }
}

/**
 * 构建一个Person对象
 * @param name
 * @param age
 * @param sex
 * @constructor
 */
var Person = function (name, age, sex) {
  this.name = name;
  this.age = age;
  this.sex = sex;
  this.firstName = firstName(name);
}

