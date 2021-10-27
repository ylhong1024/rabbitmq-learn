package com.ylhong.rabbitmq.conf;

/**
 * MQ常量
 *
 * @author ylhong
 * @date 2021/10/27
 */
public class MQConstant {

    /**
     * exchange parameters
     */
    public static class ExchangeConstant {

        /**
         * If messages to this exchange cannot otherwise be routed, send them to the alternate exchange named here.
         */
        public static final String ALTERNATE_EXCHANGE = "alternate-exchange";

    }

    /**
     * queue parameters
     */
    public static class QueueConstant {

        /**
         * How long a message published to a queue can live before it is discarded (milliseconds).
         */
        public static final String X_MESSAGE_TTL = "x-message-ttl";

        /**
         * How long a queue can be unused for before it is automatically deleted (milliseconds).
         */
        public static final String X_EXPIRES = "x-expires";

        /**
         * Sets the queue overflow behaviour. This determines what happens to messages when the maximum length of a queue is reached. Valid values are drop-head, reject-publish or reject-publish-dlx. The quorum queue type only supports drop-head and reject-publish.
         */
        public static final String X_DEAD_LETTER_EXCHANGE = "x-dead-letter-exchange";

        /**
         * If set, makes sure only one consumer at a time consumes from the queue and fails over to another registered consumer in case the active one is cancelled or dies.
         */
        public static final String X_SINGLE_ACTIVE_CONSUMER = "x-single-active-consumer";

        /**
         * Optional name of an exchange to which messages will be republished if they are rejected or expire.
         */
        public static final String X_DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";

        /**
         * Optional name of an exchange to which messages will be republished if they are rejected or expire.
         */
        public static final String X_MAX_LENGTH = "x-max-length";

        /**
         * Optional name of an exchange to which messages will be republished if they are rejected or expire.
         */
        public static final String X_MAX_LENGTH_BYTES = "x-max-length-bytes";

        /**
         * Maximum number of priority levels for the queue to support; if not set, the queue will not support message priorities.
         */
        public static final String X_MAX_PRIORITY = "x-max-priority";

        /**
         * Set the queue into lazy mode, keeping as many messages as possible on disk to reduce RAM usage; if not set, the queue will keep an in-memory cache to deliver messages as fast as possible.
         */
        public static final String X_QUEUE_MODE = "x-max-priority";

        /**
         * Set the queue into master location mode, determining the rule by which the queue master is located when declared on a cluster of nodes.
         */
        public static final String X_QUEUE_MASTER_LOCATOR = "x-queue-master-locator";
    }


}
