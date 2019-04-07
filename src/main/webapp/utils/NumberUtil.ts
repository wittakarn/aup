export namespace NumberUtil {
    export const tryParseInt = (value: string): number => {
        const valueInt = parseInt(value);
        if (isNaN(valueInt)) {
            return 0;
        }
        return valueInt;
    }
}