/*
 * Copyright 2008-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.broadleafcommerce.core.offer.domain;

import org.broadleafcommerce.common.money.Money;
import org.broadleafcommerce.core.offer.service.type.OfferDeliveryType;
import org.broadleafcommerce.core.offer.service.type.OfferDiscountType;
import org.broadleafcommerce.core.offer.service.type.OfferItemRestrictionRuleType;
import org.broadleafcommerce.core.offer.service.type.OfferType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public interface Offer extends Serializable {

    public void setId(Long id);

    public Long getId();

    public String getName();

    public void setName(String name);

    public String getDescription();

    public void setDescription(String description);

    public OfferType getType();

    public void setType(OfferType offerType);

    public OfferDiscountType getDiscountType();

    public void setDiscountType(OfferDiscountType type);

    public BigDecimal getValue();

    public void setValue(BigDecimal value);

    public int getPriority();

    public void setPriority(int priority);

    public Date getStartDate();

    public void setStartDate(Date startDate);

    public Date getEndDate();

    public void setEndDate(Date endDate);

    /**
     * @deprecated
     * Use isCombinable instead.
     * @return
     */
    public boolean isStackable();

    /**
     * @deprecated
     * calls {@link #setCombinableWithOtherOffers(boolean)}
     * @param stackable
     */
    public void setStackable(boolean stackable);

    public String getTargetSystem();

    public void setTargetSystem(String targetSystem);

    public boolean getApplyDiscountToSalePrice();

    public void setApplyDiscountToSalePrice(boolean applyToSalePrice);

    @Deprecated
    public String getAppliesToOrderRules();

    @Deprecated
    public void setAppliesToOrderRules(String appliesToRules);

    @Deprecated
    public String getAppliesToCustomerRules();

    @Deprecated
    public void setAppliesToCustomerRules(String appliesToCustomerRules);

    @Deprecated
    public boolean isApplyDiscountToMarkedItems();

    @Deprecated
    public void setApplyDiscountToMarkedItems(boolean applyDiscountToMarkedItems);
    
    public OfferItemRestrictionRuleType getOfferItemQualifierRuleType();

    public void setOfferItemQualifierRuleType(OfferItemRestrictionRuleType restrictionRuleType);
    
    public OfferItemRestrictionRuleType getOfferItemTargetRuleType();

    public void setOfferItemTargetRuleType(OfferItemRestrictionRuleType restrictionRuleType);

    /**
     * Returns false if this offer is not combinable with other offers of the same type.
     * For example, if this is an Item offer it could be combined with other Order or FG offers
     * but it cannot be combined with other Item offers.
     * 
     * @return
     */
    public boolean isCombinableWithOtherOffers();

    public void setCombinableWithOtherOffers(boolean combinableWithOtherOffers);

    /**
     * Returns true if the offer system should automatically add this offer for consideration (versus requiring a code or 
     * other delivery mechanism).    This does not guarantee that the offer will qualify.   All rules associated with this
     * offer must still pass.   A true value here just means that the offer will be considered.
     * 
     * For backwards compatibility, if the underlying property is null, this method will check the 
     * {@link #getDeliveryType()} method and return true if that value is set to AUTOMATIC.    
     * 
     * If still null, this value will return false.
     * 
     * @return
     */
    public boolean isAutomaticallyAdded();

    /**
     * Sets whether or not this offer should be automatically considered for consideration (versus requiring a code or 
     * other delivery mechanism).
     * @see #isAutomaticallyAdded()
     */
    public void setAutomaticallyAdded(boolean automaticallyAdded);

    /**
     * @deprecated Replaced by isAutomaticallyApplied property.   In prior versions of Broadleaf deliveryType was used to 
     * differentiate "automatic" orders from those requiring a code.   If the underlying property is null, 
     * this method will return a delivery type based on the isAutomatic property. 
     * @return
     */
    public OfferDeliveryType getDeliveryType();

    /**
     * @deprecated Replaced by setAutomaticallyApplied(boolean val).
     * @param deliveryType
     */
    public void setDeliveryType(OfferDeliveryType deliveryType);

    /**
     * Returns the maximum number of times that this offer
     * can be used by the same customer.   This field
     * tracks the number of times the offer can be used
     * and not how many times it is applied.
     *
     * 0 or null indicates unlimited usage per customer.
     *
     * @return
     */
    public Long getMaxUsesPerCustomer();

    /**
     * Sets the maximum number of times that this offer
       can be used by the same customer.  Intended as a transient
     * field that gets derived from the other persisted max uses fields
     * including maxUsesPerOrder and maxUsesPerCustomer.
     *
     * 0 or null indicates unlimited usage.
     *
     * @param maxUses
     */
    public void setMaxUsesPerCustomer(Long maxUses);

    /**
     * Returns the maximum number of times that this offer
     * can be used in the current order.
     *
     * 0 indicates unlimited usage.
     *
     * @return
     */
    public int getMaxUses() ;

    /**
     * Sets the maximum number of times that this offer
     * can be used in the current order.
     *
     * 0 indicates unlimited usage.
     *
     * @param maxUses
     */
    public void setMaxUses(int maxUses) ;

    @Deprecated
    public int getUses() ;

    @Deprecated
    public void setUses(int uses) ;

    public Set<OfferItemCriteria> getQualifyingItemCriteria();

    public void setQualifyingItemCriteria(Set<OfferItemCriteria> qualifyingItemCriteria);

    public Set<OfferItemCriteria> getTargetItemCriteria();

    public void setTargetItemCriteria(Set<OfferItemCriteria> targetItemCriteria);
    
    public Boolean isTotalitarianOffer();

    public void setTotalitarianOffer(Boolean totalitarianOffer);
    
    public Map<String, OfferRule> getOfferMatchRules();

    public void setOfferMatchRules(Map<String, OfferRule> offerMatchRules);
    
    public Boolean getTreatAsNewFormat();

    public void setTreatAsNewFormat(Boolean treatAsNewFormat);
    
    /**
     * Indicates the amount of items that must be purchased for this offer to
     * be considered for this order.
     * 
     * The system will find all qualifying items for the given offer and sum their prices before
     * any discounts are applied to make the determination.  
     * 
     * If the sum of the qualifying items is not greater than this value the offer is 
     * not considered by the offer processing algorithm.
     * @return
     */
    public Money getQualifyingItemSubTotal();
    
    public void setQualifyingItemSubTotal(Money qualifyingItemSubtotal);

    void setMarketingMessage(String marketingMessage);

    String getMarketingMessage();

}
