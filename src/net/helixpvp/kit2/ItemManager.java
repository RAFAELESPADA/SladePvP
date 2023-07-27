package net.helixpvp.kit2;


import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemManager {
  private String name;
  
  private List<String> lore;
  
  private Material material;
  
  private ItemStack item;
  
  private ItemMeta itemm;
  
  private boolean permissions;
  
  private String permissao;
  
  public ItemManager(Material mat, String name) {
    this.material = mat;
    this.name = name;
    this.lore = new ArrayList<>();
    ItemStack stack = new ItemStack(mat);
    ItemMeta stackmeta = stack.getItemMeta();
    stackmeta.setDisplayName(name);
    this.item = stack;
    this.itemm = stackmeta;
    this.permissions = false;
  }
  
  public Material getMaterial() {
    return this.material;
  }
  
  public void setMaterial(Material material) {
    this.material = material;
  }
  
  public void setNome(String name) {
    this.name = name;
  }
  
  public void resetLore() {
    this.lore.clear();
  }
  
  public ItemManager addLore(String lore) {
    this.lore.add("§f" + lore);
    return this;
  }
  
  public void setAmount(int amount) {
    this.item.setAmount(amount);
  }
  
  public void addPermission(String permissao) {
    this.permissions = true;
    this.permissao = permissao;
  }
  
  public void removePermission() {
    this.permissions = false;
    this.permissao = null;
  }
  
  public ItemManager setDurability(int durability) {
    this.item.setDurability((short)durability);
    return this;
  }
  
  public ItemManager addEnchantment(Enchantment enchantment, int level) {
    this.itemm.addEnchant(enchantment, level, true);
    return this;
  }
  
  public ItemStack build() {
    this.itemm.setLore(this.lore);
    this.item.setType(this.material);
    this.itemm.setDisplayName(this.name);
    this.item.setItemMeta(this.itemm);
    return this.item;
  }
  
  public ItemStack build(Player p) {
    if (!this.permissions)
      return build(); 
    this.itemm.setLore(this.lore);
    this.item.setType(this.material);
    this.itemm.setDisplayName(this.name);
    this.item.setItemMeta(this.itemm);
    if (p.hasPermission(this.permissao))
      return this.item; 
    return new ItemStack(Material.AIR);
  }
  
  public ItemManager setLore(List<String> kitInfo) {
    this.lore = new ArrayList<>();
    for (String list : this.lore)
      addLore(list); 
    return this;
  }
}
