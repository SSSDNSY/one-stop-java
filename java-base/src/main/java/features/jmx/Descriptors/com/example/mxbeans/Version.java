/*
 * Copyright (c) 2004, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/**
 * Version.java - This annotation allows to supply
 * the current version of the MBean interface.
 */

package features.jmx.Descriptors.com.example.mxbeans;

import javax.management.DescriptorKey;
import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Version {
    @DescriptorKey("version")
    String value();
}
