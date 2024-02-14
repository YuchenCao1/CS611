/**
 * Used to create monsters in the factory
 */
public final class MonsterFactory {
    public static <T extends Monster> Monster createMonster(Class<T> monsterType, final Monster.MonsterBuilder monsterBuilder){
        switch (monsterType.getName()){
            case "Spirit":
                return new Spirit(monsterBuilder);
            case "Exoskeleton":
                return new Exoskeleton(monsterBuilder);
            case "Dragon":
                return new Dragon(monsterBuilder);
            default:
                throw new IllegalArgumentException("Unknown monster type "+monsterType.getTypeName());
        }
    }
}
