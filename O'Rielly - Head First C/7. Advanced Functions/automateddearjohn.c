#include <stdio.h>
#include <string.h>
#include <stdlib.h>

enum response_type
{
    DUMP,
    SECOND_CHANCE,
    MARRIGE
};
typedef struct
{
    char *name;
    enum response_type type;
} response;

void dump(response r)
{
    printf("Dear %s,\n", r.name);
    puts("Unfortunately your last date contacted us to");
    puts("say that they will not be seeing you again");
}

void second_chance(response r)
{
    printf("Dear %s,\n", r.name);
    puts("Good news: your last date has asked us to");
    puts("arrange another meeting. Please call ASAP.");
}
void marriage(response r)
{
    printf("Dear %s,\n", r.name);
    puts("Congratulations! Your last date has contacted");
    puts("us with a proposal of marriage.");
}

void (*replies[])(response) = {dump, second_chance, marriage};

int main(int argc, char const *argv[])
{
    response r[] = {
        {"Mike", DUMP},
        {"Luis", SECOND_CHANCE},
        {"Matt", SECOND_CHANCE},
        {"William", MARRIGE}};
    for (int i = 0; i < 4; i++)
    {
        replies[r[i].type](r[i]);
    }

    return 0;
}
