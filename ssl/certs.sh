#!/usr/bin/env bash
CERTS_OUTPUT=$HOME/Desktop/certs
rm -rf $CERTS_OUTPUT
mkdir -p $CERTS_OUTPUT
cd $CERTS_OUTPUT
KEY_COUNTER_FILE=`dirname $CERTS_OUTPUT`/key_counter
ls -la  $KEY_COUNTER_FILE || echo "0" >> $KEY_COUNTER_FILE
KEY_COUNTER_VALUE=$(cat $KEY_COUNTER_FILE)
((KEY_COUNTER_VALUE+=1))
echo $KEY_COUNTER_VALUE  > $KEY_COUNTER_FILE

openssl req -x509 -subj "/CN=bootiful-${KEY_COUNTER_VALUE}" -keyout bootiful.key -out bootiful.crt -sha256 -days 365 -nodes -newkey ed25519