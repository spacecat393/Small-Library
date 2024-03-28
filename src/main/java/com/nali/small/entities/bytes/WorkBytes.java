package com.nali.small.entities.bytes;

public interface WorkBytes
{
//STATE
    byte LOCK_INVENTORY();
    byte LOCK_DAMAGE();
    byte WALK_TO();
    byte USE_TO();
//ACTION
    byte SIT();
    byte LOCATION();
    byte FOLLOW();
    byte REVIVE();
    byte PLAY();
    byte HEAL();
    byte PROTECT();
    byte CARE_OWNER();
    byte ATTACK();
    byte MANAGE_ITEM();
    byte GET_ITEM();
    byte FISHING();
    byte PLANT();
    byte MINE();
    byte RANDOM_WALK();
    byte LOOK_TO();
    byte RANDOM_LOOK();
    byte MAX_WORKS();
}
