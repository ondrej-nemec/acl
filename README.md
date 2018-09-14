# Access control list
**Newest version:** 1.0

* [Description](#description)
* [Get library](#how-to-install)
* [Usage](#usage)
	 * [AuthorizationHelper]
	 * [Interfaces]
	 * [Rules](#rules)

## Description
Simply package providing authorizations. You just define rules and hierarchy. [See usage](#usage)
## How to install
### Download:

<a href="https://ondrej-nemec.github.io/download/acl-1.0.jar" target=_blank>Download jar</a>
### Maven:

```xml
<dependency>
  <groupId>io.github.ondrej-nemec.acl</groupId>
  <artifactId>acl</artifactId>
  <version>1.0</version>
</dependency>
```

## Usage
### AuthorizationHelper
All authorization logic do `AuhtorizationHelper` class.
```java
new AuthorizationHelper(final Rules rules);
new AuthorizationHelper(final Rules rules, Logger logger);
```
This class has two method. No matter which you choose to use (or both).
```java
boolean isAllowed(final UserInterface who, final DestinationInterface where, final Action what);

void throwIfIsNotAllowed(final UserInterface who, final DestinationInterface where, final Action what) throws AccessDeniedException;
```
If logger is specified, if someone call some from previous method, INFO will be logged with 'who', 'where' and 'what'. If no rule for user is found, WARNING will be logged.
**Order for searching right rule for user:**
1. rule for exactly this user
2. rule for this user and every with higher rank
3. rule for exactly user's role
4. rule for user's role and every role with higher rank
[More about Rules](#rules)

### Interfaces
This solution require that you implements some interfaces. This allow you customize function of this package.
`UserInterface` need unique 'id'. Than has 'rank'. If you haven't got rules for hierarchy, this value could be f.e. zero. Last required attribute is `List<Role>`. **If you have no roles, let it be empty array.**
`RoleInterface` need unique 'id'. Than has 'rank'. If you haven't got rules for hierarchy, this value could be f.e. zero.
`DestinationInterface` need only 'id'.
Every 'id' are strings, so it's up to you how will be represented.

### Rules
Last and very important interface is `Rules`. This interface provide information if given user has rule for given destination with given action. For this has four methods:
```java
default Status getRuleUserId(String userId, String destinationId, Action action) {
	return Status.UNSPECIFIED;
}
	
default Status getRuleUserRank(int userRank, String destinationId, Action action) {
	return Status.UNSPECIFIED;
}
	
default Status getRuleRoleId(String roleId, String destinationId, Action action) {
	return Status.UNSPECIFIED;
}
	
default Status getRuleRoleRank(int roleRank, String destinationId, Action action) {
	return Status.UNSPECIFIED;
}
```
As you see you could choose which feature you use. `AuthorizationHelper` asks `Rules` in given order (order is mentions above) if exist rule for given user (or role) on given destination with given action. `Rules` answer `ALLOWED` or `DISALLOWED` and `AuthorizationHelper` end with returned state (allowed/disallowed) or `UNSPECIFIED` is returned and `AuthororizationHelper` continue in searching.