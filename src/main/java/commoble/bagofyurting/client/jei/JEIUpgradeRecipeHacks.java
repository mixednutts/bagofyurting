package commoble.bagofyurting.client.jei;

import java.util.Arrays;

import commoble.bagofyurting.BagOfYurtingItem;
import commoble.bagofyurting.BagOfYurtingUpgradeRecipe;
import commoble.bagofyurting.ItemRegistrar;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;

public class JEIUpgradeRecipeHacks
{
	public static BagOfYurtingUpgradeRecipe getFakeRecipe(BagOfYurtingUpgradeRecipe recipe, int outputRadius)
	{
		return new BagOfYurtingUpgradeRecipe(recipe.getId(), recipe.getGroup(), recipe.getWidth(), recipe.getHeight(), convertIngredients(recipe.getIngredients(), outputRadius), ItemRegistrar.BAG_OF_YURTING.withRadius(recipe.getRecipeOutput(), outputRadius), outputRadius);
	}
	
	public static NonNullList<Ingredient> convertIngredients(NonNullList<Ingredient> baseIngredients, int outputRadius)
	{
		NonNullList<Ingredient> list = NonNullList.create();
		baseIngredients.forEach(ingredient -> list.add(convertIngredient(ingredient, outputRadius)));
		
		return list;
	}
	
	public static Ingredient convertIngredient(Ingredient ingredient, int outputRadius)
	{
		ItemStack[] stacks = Arrays.stream(ingredient.getMatchingStacks())
			.map(stack -> JEIUpgradeRecipeHacks.convertIngredientStack(stack, outputRadius))
			.toArray(ItemStack[]::new);
		return Ingredient.fromStacks(stacks);
	}

	public static ItemStack convertIngredientStack(ItemStack baseIngredient, int outputRadius)
	{
		if (baseIngredient.getItem() instanceof BagOfYurtingItem)
		{
			return ItemRegistrar.BAG_OF_YURTING.withRadius(baseIngredient, outputRadius-1);
		}
		else
		{
			return baseIngredient;
		}
	}
}
