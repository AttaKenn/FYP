// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import org.knowm.xchange.dto.marketdata.Trade;

/**
 * DTO - Data transfer object to carry to @Trade object and some other information like exchange
 */
public class TradeLoad implements Serializable {

    /**
     * Trade DTO from org.knowm.xchange.dto.marketdata.Trade
     */
    //private Trade trade;

    /**
     * Bigtable row key name used for exchange
     */
    private String exchange;

    private BigDecimal price;

    private BigDecimal volume;
    private Date timestampExchange;
    private long timestampDelta;
    private String type;

    private String currencyPair;


    public Date getTimestampSystem() {
        return timestampSystem;
    }

    public void setTimestampSystem(Date timestampSystem) {
        this.timestampSystem = timestampSystem;
    }

    private Date timestampSystem;


    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getTimestampExchange() {
        return timestampExchange;
    }

    public void setTimestampExchange(Date timestampExchange) {
        this.timestampExchange = timestampExchange;
    }

    public long getTimestampDelta() {
        return timestampDelta;
    }

    public void setTimestampDelta(long timestampDelta) {
        this.timestampDelta = timestampDelta;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }





    /**
     *
     * @param trade - Trade DTO from org.knowm.xchange.dto.marketdata.Trade
     * @param exchange - Bigtable row key name used for exchange
     */
    public TradeLoad(Trade trade, String exchange) {
        super();

        this.price = trade.getPrice();
        this.volume = trade.getOriginalAmount();

        long delta =  System.currentTimeMillis() - trade.getTimestamp().getTime();
        this.setTimestampDelta(delta);

        this.timestampSystem = new Timestamp(System.currentTimeMillis());

        this.timestampExchange = trade.getTimestamp();

        this.exchange = exchange;
        this.type = trade.getType().toString();
        this.currencyPair = trade.getCurrencyPair().toString();
    }

    public TradeLoad() {
        super();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }

    public void setCurrencyPair(String currencyPair) {
        this.currencyPair = currencyPair;
    }
}