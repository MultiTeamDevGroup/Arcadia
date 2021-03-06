package net.minecraft.world.server;

import java.util.Objects;

public final class Ticket<T> implements Comparable<Ticket<?>> {
   private final TicketType<T> type;
   private final int level;
   private final T value;
   private long timestamp;

   protected Ticket(TicketType<T> p_i226095_1_, int p_i226095_2_, T p_i226095_3_) {
      this(p_i226095_1_, p_i226095_2_, p_i226095_3_, false);
   }

   public Ticket(TicketType<T> p_i226095_1_, int p_i226095_2_, T p_i226095_3_, boolean forceTicks) {
      this.type = p_i226095_1_;
      this.level = p_i226095_2_;
      this.value = p_i226095_3_;
      this.forceTicks = forceTicks;
   }

   public int compareTo(Ticket<?> p_compareTo_1_) {
      int i = Integer.compare(this.level, p_compareTo_1_.level);
      if (i != 0) {
         return i;
      } else {
         int j = Integer.compare(System.identityHashCode(this.type), System.identityHashCode(p_compareTo_1_.type));
         return j != 0 ? j : this.type.getComparator().compare(this.value, (T)p_compareTo_1_.value);
      }
   }

   public boolean equals(Object p_equals_1_) {
      if (this == p_equals_1_) {
         return true;
      } else if (!(p_equals_1_ instanceof Ticket)) {
         return false;
      } else {
         Ticket<?> ticket = (Ticket)p_equals_1_;
         return this.level == ticket.level && Objects.equals(this.type, ticket.type) && Objects.equals(this.value, ticket.value) && this.forceTicks == ticket.forceTicks;
      }
   }

   public int hashCode() {
      return Objects.hash(this.type, this.level, this.value, forceTicks);
   }

   public String toString() {
      return "Ticket[" + this.type + " " + this.level + " (" + this.value + ")] at " + this.timestamp + " force ticks " + forceTicks;
   }

   public TicketType<T> getType() {
      return this.type;
   }

   public int getLevel() {
      return this.level;
   }

   protected void setTimestamp(long p_229861_1_) {
      this.timestamp = p_229861_1_;
   }

   protected boolean isExpired(long currentTime) {
      long i = this.type.getLifespan();
      return i != 0L && currentTime - this.timestamp > i;
   }

   /* ======================================== FORGE START =====================================*/
   private final boolean forceTicks;

   public boolean isForceTicks() {
      return forceTicks;
   }
}
