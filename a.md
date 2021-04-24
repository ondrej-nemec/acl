### rules
Rules: userId - groupId - minimal level -- domain - action
Owners: rule_id - userId - groupId - maximal level

select action, null as minimalRank, owner
from rules r
left join owners o on r.id = o.rule_id
where destination = destination and (userId = userId or userId = '')
	union
select action, minimalRank, owner
from rules r
left join owners o on r.id = o.rule_id
join groups g on r.groupId = g.id
join ... users...
where destination = destination and (u.id = userId or groupId = '')


select userId
from owner o
	union
select u.id
from owner o
left join groups on g.id = o.group_id
join ... users...
where u.rank <= maximal_rank and rule_id = ruleId

userId, groupId - null - nepouzito, empty - vsichni

- kombinace
  uzivalel -- owner
  skupina -- owner
  skupina - level -- owner
  uzivatel - skupina - level -- owner

public static AccessRule withoutLevel(Action action, List<String> owners) {
	return new AccessRule(null, action, owners);
}
	
public static AccessRule withLevel(int rank, Action action, List<String> owners) {
	return new AccessRule(rank, action, owners);
}
*****
public static Rules forUserWithOwner(Action forUser, List<String> owners) {
	return new Rules(forUser, owners, new LinkedList<>());
}

public static Rules forUserGroupsAndLevels(List<AccessRule> access) {
	return new Rules(Action.UNDEFINED, null, access);
}

### user specification

zastupne znaky
 any-uzivatel
 any-group
 
 napr:
 any-uzivatel edit any-uzivatel
 kazdy uzivatel muze editovat sama sebe
 
 any-group min level 2 any group max-level1
 kazdy s level 2 muze editovat kohokoli z te same skupiny s level 1

// boolean s negaci
user equals
group in
level maximal
group in and level maximal


userId - groupId - maximal level - not (boolean)


-> get list ids uzivatelu, kde muze danou akci provest
-> lze provest akci pro daneho uzivatele

uzivatel muze pridat milestone pokud
 - pridava sam sobe
 - pridava nekomu podrizenemu a ve stejne skupine

// administrativa 
uzivalel muze editovat uzivatele pokud
 - je ve skupine