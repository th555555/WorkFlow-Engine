/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package net.risesoft.model.processadmin;

/**
 * Contains constants for all types of identity links that can be used to involve a user or group with a certain object.
 * 
 * see e.g. TaskService#addUserIdentityLink(String, String, String) see e.g. TaskService#addGroupIdentityLink(String,
 * String, String)}
 * 
 * @author Joram Barrez
 */
public class IdentityLinkType {

    /**
     * 办理人
     */
    public static final String ASSIGNEE = "assignee";

    /**
     * 参与人
     */
    public static final String CANDIDATE = "candidate";

    /**
     * 拥有者
     */
    public static final String OWNER = "owner";

    /**
     * 启动人
     */
    public static final String STARTER = "starter";

    /**
     * 参与者
     */
    public static final String PARTICIPANT = "participant";

    public static final String REACTIVATOR = "reactivator";
}
