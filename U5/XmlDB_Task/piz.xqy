for $b in doc (pizzen.xml)
where 
$b/pizza/pizzaId =1
return <pizza>
{$b/pizzaId}
{$b/pizzaName}
</pizza>