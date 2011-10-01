ALTER TABLE parameters
  DROP CONSTRAINT IF EXISTS parameters_kind_check,
  ADD CONSTRAINT  parameters_kind_check
        CHECK (kind::text = ANY (ARRAY[
                    'RADIO'::character varying,
                    'CHECK_BOX'::character varying,
                    'DATA_SPAN'::character varying]::text[]) );

INSERT INTO parameters(
            id, title, kind, description)
    VALUES (1, 'ParameterTitle', 'DATA_SPAN', 'fake DATA_SPAN parameter');


INSERT INTO parameter_values(
            id, int_value, text_value, parameter_id)
    VALUES (1, 11, 'one one value', 1);

