/*
 * Copyright 2014 Michael Evans <michaelcevans10@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.kiwi.cabaggregator.uber.entities;

public class Product {
    private String product_id;
    private String description;
    private String display_name;
    private int capacity;
    private String image;

    public String getProductId() {
        return product_id;
    }

    public String getDescription() {
        return description;
    }

    public String getDisplayName() {
        return display_name;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return display_name + " " + product_id;
    }
}
