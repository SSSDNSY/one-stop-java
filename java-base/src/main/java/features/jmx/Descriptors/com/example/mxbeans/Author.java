/*
 * Copyright (c) 2004, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/**
 * Author.java - This annotation allows to supply
 * the name of the creator of the MBean interface.
 */

package features.jmx.Descriptors.com.example.mxbeans;

import javax.management.DescriptorKey;
import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Author {
    @DescriptorKey("author")
    String value();
}
