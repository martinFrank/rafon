package com.github.martinfrank.logic;

import com.github.martinfrank.data.entity.Combat;
import com.github.martinfrank.data.entity.Item;
import com.github.martinfrank.data.entity.Player;
import com.github.martinfrank.data.service.RepositoryService;

public class CombatHandler {

    public RoundResult handleRound(Player currentPlayer, RepositoryService service) {

        //Lets assume we have a butterfly knife equipped
        Item playersWeapon = service.getItemRepository().findByName("butterfly knife");
        Combat combat = currentPlayer.getCombat();
        CombatEntity player = new CombatEntity(
                currentPlayer.getDisplayName(),
                combat.getOpponentLife(),
                new Die(playersWeapon.getAttack()),
                new Die(playersWeapon.getDamage()),
                new Die(playersWeapon.getDefense()),
                new Die(playersWeapon.getArmor()));

        CombatEntity opponent = new CombatEntity(
                combat.getName(),
                combat.getOpponentLife(),
                new Die(combat.getOpponent().getAttack()),
                new Die(combat.getOpponent().getDamage()),
                new Die(combat.getOpponent().getDefense()),
                new Die(combat.getOpponent().getArmor()));

        RoundResult result = new RoundResult(player,opponent);



        //initiative
        CombatEntity first = player;
        CombatEntity second = opponent;

        result.add(first.getName()+" won initiative, so first goes "+first.getName()+" then "+second.getName());

        performAttack(first, second, result);
        if(!second.isDead()){
            performAttack(second, first, result);
        }

        if(opponent.isDead()){
            currentPlayer.setCombat(null);
            service.getPlayerRepository().save(currentPlayer);
            service.getCombatRepository().delete(combat);
            result.setVictory(true);
        }else {
            combat.setOpponentLife(opponent.getLife());
            service.getCombatRepository().save(combat);
        }
        currentPlayer.setCurrentLife(player.getLife());
        service.getPlayerRepository().save(currentPlayer);

        //save results

//        currentPlayerLife = currentPlayerLife - 2d;
//        currentPlayer.getCombat().setOpponentLife(currentPlayerLife);
//        service.getCombatRepository().save(currentPlayer.getCombat());
//
//        //FIXME move to method
//        combatText.setText("you are fighting " + currentPlayer.getCombat().getOpponent().getName() + ":" + currentPlayer.getCombat().getOpponentLife());
//
//        if (currentPlayer.getCombat().getOpponentLife() <= 0) {
//            Combat combat = currentPlayer.getCombat();
//            currentPlayer.setCombat(null);
//            service.getPlayerRepository().save(currentPlayer);
//            service.getCombatRepository().delete(combat);
//            Notification.show("victory!!");
//
//
//            //from here
//            Item butterflyKnife = service.getItemRepository().findByName("butterfly knife");
//            PlayerItem playerItem = new PlayerItem();
//            playerItem.setItem(butterflyKnife);
//            playerItem.setPlayer(currentPlayer);
//            Set<PlayerItem> playerItems = currentPlayer.getPlayerItems();
//            if (playerItems == null) {
//                playerItems = new HashSet<>();
//                currentPlayer.setPlayerItems(playerItems);
//            }
//            playerItems.add(playerItem);
//            service.getPlayerItemRepository().save(playerItem);
//            service.getPlayerRepository().save(currentPlayer);
//
//            recreatePage();
//        }
        return result;
    }

    private void performAttack(CombatEntity first, CombatEntity second, RoundResult result) {
        int attack = first.getAttack().result();
        int defense = second.getDefense().result();

        if(attack > defense){
            result.add("attack was successful ("+attack+" of "+first.getAttack().toString()+" vs "+defense+" of "+second.getDefense().toString()+")");
            int damage = first.getDamage().result();
            int armor = second.getArmor().result();
            if(damage > armor){
                result.add("attack successfully pierced armor ("+damage+" of "+first.getDefense().toString()+" vs "+armor+" of "+second.getArmor().toString()+")");
                int damageDealt = damage - armor;
                result.add("damage dealt from "+first.getName()+" to "+second.getName()+" was "+damageDealt);
                second.dealDamage(damageDealt);
            }else{
                result.add("attack could not pierce armor ("+damage+" of "+first.getDefense().toString()+" vs "+armor+" of "+second.getArmor().toString()+")");
            }
        }else{
            result.add("attack failed ("+attack+" of "+first.getAttack().toString()+" vs "+defense+" of "+second.getDefense().toString()+")");
        }
    }
}
