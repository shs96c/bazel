// Copyright 2015 The Bazel Authors. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.devtools.build.lib.packages;

import static java.nio.charset.StandardCharsets.UTF_8;

import com.google.common.io.Files;
import com.google.devtools.build.lib.analysis.config.BuildConfiguration;

import com.google.devtools.build.lib.bazel.BazelMain;
import com.google.devtools.build.lib.bazel.rules.BazelRuleClassProvider;
import com.google.devtools.build.lib.packages.util.DocumentationTestUtil;
import com.google.devtools.build.lib.util.OS;
import com.google.devtools.build.lib.windows.util.WindowsTestUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;

/**
 * Test for Bazel documentation.
 */
@RunWith(JUnit4.class)
public class BazelDocumentationTest {
  /**
   * Checks that the blaze-user-manual is in sync with the
   * {@link BuildConfiguration}.
   */
  @Test
  public void testBazelUserManual() throws Exception {
    String documentationFilePath = "site/docs/bazel-user-manual.html";
    if (OS.getCurrent() == OS.WINDOWS) {
      documentationFilePath = WindowsTestUtil.getRunfile("io_bazel/" + documentationFilePath);
    }
    final File documentationFile = new File(documentationFilePath);
    DocumentationTestUtil.validateUserManual(
        BazelMain.BAZEL_MODULES,
        BazelRuleClassProvider.create(),
        Files.asCharSource(documentationFile, UTF_8).read());
  }
}
