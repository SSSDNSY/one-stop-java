/*
 * Copyright (c) 2004, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/**
 * DisplayName.java - This annotation allows to supply
 * a display name for a method in the MBean interface.
 */

package features.jmx.descriptors.com.example.mxbeans;

import javax.management.DescriptorKey;
import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DisplayName {
    @DescriptorKey("displayName")
    String value();
}
